package id.nopal.belajartka

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.math.cos
import kotlin.math.sin

/** What Tuti is doing. */
enum class MascotMood { IDLE, CORRECT, WRONG, DONE }

private val Outline = Color(0xFF1A1D24)
private val ArmRed = Color(0xFFB23943)
private val PencilBody = Color(0xFFF4B63A)
private val PencilWood = Color(0xFFF2D2A9)

/** Shoulders sit just behind the cover edge, in the 200 x 240 units of the drawing. */
private val RightShoulder = Offset(157f, 104f)
private val LeftShoulder = Offset(49f, 118f)

/** One arm: direction in degrees (0 = right, -90 = up), length, and how far the elbow bows out. */
private class ArmPose(val angle: Float, val length: Float, val bend: Float)

private fun rightPose(m: MascotMood) = when (m) {
    MascotMood.IDLE -> ArmPose(-55f, 48f, -6f)
    MascotMood.CORRECT -> ArmPose(-68f, 52f, -10f)
    MascotMood.WRONG -> ArmPose(62f, 44f, -6f)
    MascotMood.DONE -> ArmPose(-72f, 52f, 0f)
}

private fun leftPose(m: MascotMood) = when (m) {
    MascotMood.IDLE -> ArmPose(120f, 36f, 6f)
    MascotMood.CORRECT -> ArmPose(-140f, 40f, 8f)
    MascotMood.WRONG -> ArmPose(108f, 36f, -4f)
    MascotMood.DONE -> ArmPose(-145f, 40f, 0f)
}

/** How far each arm sways around its pose, in degrees. A sad Tuti barely moves. */
private fun sway(m: MascotMood) = when (m) {
    MascotMood.IDLE -> 8f
    MascotMood.CORRECT, MascotMood.DONE -> 12f
    MascotMood.WRONG -> 2f
}

/**
 * Tuti si Buku, the app's own character (see README, "Karakter").
 * Body and face are vectors (res/drawable/tuti_*); the arms are drawn here as bendy curves so they can move:
 * they sway all the time and spring into a new pose when the mood changes (up for a right answer,
 * hanging for a wrong one). The whole figure also breathes, blinks, hops and shakes.
 * The canvas leaves room on the sides and on top, so no frame is clipped. Motion only: no text, no confetti.
 */
@Composable
fun Mascot(mood: MascotMood, modifier: Modifier = Modifier, size: Dp = 96.dp) {
    val legs = painterResource(R.drawable.tuti_legs)
    val idle = painterResource(R.drawable.tuti_body_idle)
    val blink = painterResource(R.drawable.tuti_body_blink)
    val happy = painterResource(R.drawable.tuti_body_happy)
    val sad = painterResource(R.drawable.tuti_body_sad)

    val loop = rememberInfiniteTransition(label = "tuti")
    val breath by loop.animateFloat(0f, 1f, infiniteRepeatable(tween(1600, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "breath")
    val wave by loop.animateFloat(-1f, 1f, infiniteRepeatable(tween(1300, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "wave")
    // Blink on an infinite clock rather than a delay loop, so UI tests can still go idle.
    val clock by loop.animateFloat(0f, 1f, infiniteRepeatable(tween(3600, easing = LinearEasing)), label = "blink")

    val hop = remember { Animatable(0f) }    // 1 = top of a hop
    val shake = remember { Animatable(0f) }  // -1..1, a tilt left or right
    val rA = remember { Animatable(rightPose(MascotMood.IDLE).angle) }
    val rL = remember { Animatable(rightPose(MascotMood.IDLE).length) }
    val rB = remember { Animatable(rightPose(MascotMood.IDLE).bend) }
    val lA = remember { Animatable(leftPose(MascotMood.IDLE).angle) }
    val lL = remember { Animatable(leftPose(MascotMood.IDLE).length) }
    val lB = remember { Animatable(leftPose(MascotMood.IDLE).bend) }
    val amp = remember { Animatable(sway(MascotMood.IDLE)) }
    LaunchedEffect(mood) {
        hop.snapTo(0f); shake.snapTo(0f)
        coroutineScope {
            // A loose spring, so the arms overshoot a little and settle like real limbs.
            val arms = spring<Float>(dampingRatio = 0.45f, stiffness = Spring.StiffnessMediumLow)
            val r = rightPose(mood); val l = leftPose(mood)
            listOf(rA to r.angle, rL to r.length, rB to r.bend, lA to l.angle, lL to l.length, lB to l.bend, amp to sway(mood))
                .forEach { (a, v) -> async { a.animateTo(v, arms) } }
            when (mood) {
                MascotMood.IDLE -> Unit
                MascotMood.CORRECT -> hop.animateTo(0f, keyframes { durationMillis = 520; 1f at 200; 0f at 420; 0.12f at 470 })
                MascotMood.WRONG -> shake.animateTo(0f, keyframes { durationMillis = 600; -1f at 100; 1f at 220; -0.6f at 340; 0.3f at 460 })
                MascotMood.DONE -> {
                    hop.animateTo(0f, keyframes { durationMillis = 900; 1f at 200; 0f at 400; 0.7f at 580; 0f at 760; 0.1f at 830 })
                    shake.animateTo(0f, keyframes { durationMillis = 400; 0.4f at 100; -0.4f at 220; 0.2f at 320 })
                }
            }
        }
    }

    val body = when (mood) {
        MascotMood.IDLE -> if (clock > 0.96f) blink else idle
        MascotMood.CORRECT, MascotMood.DONE -> happy
        MascotMood.WRONG -> sad
    }
    val rim = C.mascotRim
    val rimOn = C.dark
    Canvas(modifier.size(size * 1.3f, size * 1.42f).testTag("mascot")) {
        val w = this.size.width / 1.3f
        val h = w * 1.2f
        val left = (this.size.width - w) / 2
        val top = this.size.height - h
        val right = ArmPose(rA.value + wave * amp.value, rL.value, rB.value + wave * 3f)
        val leftArm = ArmPose(lA.value - wave * amp.value * 0.6f, lL.value, lB.value)
        withTransform({
            translate(left, top - hop.value * h * 0.12f)
            rotate(shake.value * 6f, Offset(w / 2, h))
            scale(1f, 1f + breath * 0.02f, Offset(w / 2, h))
        }) {
            val fig = Size(w, h)
            if (rimOn) {
                val r = 2.dp.toPx()
                for ((dx, dy) in RIM) translate(dx * r, dy * r) { figure(fig, legs, body, right, leftArm, rim) }
            }
            figure(fig, legs, body, right, leftArm, null)
        }
    }
}

/** Legs, then arms and pencil, then the book on top, so the shoulders tuck behind the cover. [tint] paints a flat rim. */
private fun DrawScope.figure(fig: Size, legs: Painter, body: Painter, right: ArmPose, left: ArmPose, tint: Color?) {
    val filter = tint?.let { ColorFilter.tint(it) }
    with(legs) { draw(fig, colorFilter = filter) }
    val k = fig.width / 200f
    withTransform({ scale(k, k, Offset.Zero) }) {
        arm(LeftShoulder, left, 8f, tint)
        val hand = handOf(RightShoulder, right)
        pencil(hand, right.angle - 30f, tint)
        arm(RightShoulder, right, 9f, tint)
    }
    with(body) { draw(fig, colorFilter = filter) }
}

private fun handOf(s: Offset, p: ArmPose): Offset {
    val a = Math.toRadians(p.angle.toDouble())
    return Offset(s.x + p.length * cos(a).toFloat(), s.y + p.length * sin(a).toFloat())
}

/** A rubber-hose arm: one curve stroked dark and wide, then red and narrow, ending in a round hand. */
private fun DrawScope.arm(s: Offset, p: ArmPose, handR: Float, tint: Color?) {
    val h = handOf(s, p)
    val a = Math.toRadians(p.angle.toDouble())
    val mid = (s + h) / 2f
    val ctrl = Offset(mid.x - sin(a).toFloat() * p.bend, mid.y + cos(a).toFloat() * p.bend)
    val path = Path().apply { moveTo(s.x, s.y); quadraticTo(ctrl.x, ctrl.y, h.x, h.y) }
    drawPath(path, tint ?: Outline, style = Stroke(14f, cap = StrokeCap.Round))
    drawPath(path, tint ?: ArmRed, style = Stroke(6f, cap = StrokeCap.Round))
    drawCircle(tint ?: ArmRed, handR, h)
    drawCircle(tint ?: Outline, handR, h, style = Stroke(4f))
}

/** The pencil in the right hand, pointing along [angle] degrees. */
private fun DrawScope.pencil(hand: Offset, angle: Float, tint: Color?) {
    val stroke = Stroke(4f, join = StrokeJoin.Round)
    rotate(angle + 90f, hand) {
        translate(hand.x, hand.y) {
            val shaft = Path().apply { moveTo(-7f, -22f); lineTo(7f, -22f); lineTo(7f, 12f); lineTo(-7f, 12f); close() }
            val tip = Path().apply { moveTo(-7f, -22f); lineTo(0f, -38f); lineTo(7f, -22f); close() }
            val lead = Path().apply { moveTo(-2f, -33f); lineTo(0f, -38f); lineTo(2f, -33f); close() }
            drawPath(shaft, tint ?: PencilBody); drawPath(shaft, tint ?: Outline, style = stroke)
            drawPath(tip, tint ?: PencilWood); drawPath(tip, tint ?: Outline, style = stroke)
            drawPath(lead, tint ?: Outline)
        }
    }
}

private val RIM = listOf(1f to 0f, -1f to 0f, 0f to 1f, 0f to -1f, 0.7f to 0.7f, -0.7f to 0.7f, 0.7f to -0.7f, -0.7f to -0.7f)

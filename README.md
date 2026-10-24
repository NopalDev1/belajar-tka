# Tuntas

Aplikasi Android untuk persiapan **TKA SMA 2026** (Matematika, B. Indonesia, B. Inggris, Fisika), dibuat untuk TKA gelombang 2 (2–5 November 2026).

## Unduh

**[JalurTKA.apk (versi terbaru)](https://github.com/NopalDev1/belajar-tka/releases/latest/download/JalurTKA.apk)**: buka link ini di HP, unduh, lalu pasang. Kalau diminta, izinkan "Instal dari sumber tidak dikenal" untuk browser.

## Fitur

- **Perpustakaan (tab Buku)**: empat buku sesuai kisi-kisi resmi. Matematika 12 bab, Fisika 13 bab, B. Indonesia 10 bab, B. Inggris 10 bab. Halaman bergaya kertas dengan efek balik halaman, kotak rumus, contoh soal yang dibuka langkah demi langkah, daftar jebakan, dan rangkuman.
- **Rumus LaTeX**: rumus Matematika dan Fisika digambar dengan jlatexmath (pecahan, akar, pangkat, integral sigma, dan sebagainya), di buku maupun di soal.
- **Buku B. Inggris dwibahasa**: isi dalam bahasa Inggris seperti di tes, penjelasan penting diberi terjemahan singkat bahasa Indonesia, istilah ditulis "Indonesia (English)", misalnya "Teks deskriptif (descriptive text)".
- **Peta jalur**: satu titik untuk tiap subbab, latihan di akhir tiap bab, dan paket latihan dari bank soal. Titik terbuka satu per satu.
- **Bank soal (512 soal)**: PG, PG Kompleks, dan PG Kompleks Kategori, dengan kunci dan pembahasan singkat. Sumbernya kisi-kisi dan contoh resmi, file latihan yang diunggah, dan soal yang ditulis untuk aplikasi ini dengan acuan contoh soal di detikEdu, CNN Indonesia, Kompas.com, dan Blog ITERA.
- **Progres**: level, hari berturut-turut, progres per mapel, grafik harian, lencana, dan daftar soal salah.
- **Sesi Harian**: belajar, latihan soal, lalu foto atau ketik ringkasan. Selama sesi, aplikasi disematkan dan layar tetap menyala.

## Gaya penulisan

Teks buku dan pembahasan memakai ragam buku pelajaran: tanpa emoji, tanpa kalimat pembuka atau penyemangat, angka dengan satuan, dan tips hanya jika menyebut jebakan yang spesifik. Tampilan memakai warna rata, garis 2 dp, dan sudut 12 dp. Animasi yang tersisa hanya yang memberi informasi: titik terbuka, cincin progres, balik halaman, dan langkah contoh soal. `ContentTest.textHasNoChatbotFiller` memeriksa aturan ini.

## Sumber materi

Lingkup materi mengikuti kisi-kisi TKA SMA 2026 dari Pusmendik Kemendikdasmen, sebagaimana dikutip CNN Indonesia, Medcom, Kumparan, Bisadanedu, dan Blog ITERA. Daftar lengkapnya ada di tab Progres, bagian Sumber materi.

## Karakter

Tuti si Buku (maskot dan ikon aplikasi) adalah karakter orisinal yang digambar khusus untuk aplikasi ini sebagai vektor (`res/drawable/tuti_*.xml`, `ic_launcher_*.xml`; tangan dan pensil digambar dengan kode di `Mascot.kt`). Tidak dijiplak, tidak memakai gambar stok, dan tidak meniru karakter atau merek mana pun. Gerakannya (napas, kedip, tangan melambai dan memantul, lompat, geleng) dibuat dengan kode di `Mascot.kt`.

## Build

GitHub Actions (`.github/workflows/android.yml`) menjalankan unit test, membangun APK, merilisnya sebagai `latest`, lalu menjalankan tes UI di emulator dan menyimpan screenshot QC di branch `qc-screenshots`.

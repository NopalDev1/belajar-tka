# Jalur TKA

Aplikasi Android untuk persiapan **TKA SMA 2026** (Matematika, B. Indonesia, B. Inggris, Fisika), dibuat untuk TKA gelombang 2 (2–5 November 2026).

## Unduh

**[JalurTKA.apk (versi terbaru)](https://github.com/NopalDev1/belajar-tka/releases/latest/download/JalurTKA.apk)**: buka link ini di HP, unduh, lalu pasang. Kalau diminta, izinkan "Instal dari sumber tidak dikenal" untuk browser.

## Fitur

- Peta jalur belajar ala Duolingo: 17 langkah, 24 Okt–1 Nov, terbuka satu per satu.
- Kartu materi yang bisa digeser dan ditandai "paham", lalu kuis dengan pembahasan, combo, dan XP.
- Progres interaktif: level, streak, cincin per mapel, grafik harian, lencana, peti hadiah, daftar soal salah.
- Sesi Harian: 5 jam belajar → 1 jam soal → foto atau ketik ringkasan. Aplikasi disematkan dan layar tetap menyala selama sesi, dengan tombol darurat.
- Pemblokir distraksi (Aksesibilitas): menutup aplikasi lain saat sesi, opsi blokir terus, dan batas harian.

## Sumber materi

Lingkup materi mengikuti kisi-kisi TKA SMA 2026 dari Pusmendik Kemendikdasmen, sebagaimana dikutip CNN Indonesia, Medcom, Kumparan, Bisadanedu, dan Blog ITERA. Daftar lengkapnya ada di tab Progres → Sumber materi.

## Build

GitHub Actions (`.github/workflows/android.yml`) menjalankan unit test, membangun APK, merilisnya sebagai `latest`, lalu menjalankan tes UI di emulator dan menyimpan screenshot QC di branch `qc-screenshots`.

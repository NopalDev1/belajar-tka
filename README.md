# Jalur TKA

Aplikasi Android untuk persiapan **TKA SMA 2026** (Matematika, B. Indonesia, B. Inggris, Fisika), dibuat untuk TKA gelombang 2 (2–5 November 2026).

## Unduh

**[JalurTKA.apk (versi terbaru)](https://github.com/NopalDev1/belajar-tka/releases/latest/download/JalurTKA.apk)**: buka link ini di HP, unduh, lalu pasang. Kalau diminta, izinkan "Instal dari sumber tidak dikenal" untuk browser.

## Fitur

- **Perpustakaan (tab Buku)**: empat buku, yaitu Matematika, B. Indonesia, B. Inggris, dan Fisika. Isinya disusun per bab dan subbab sesuai kisi-kisi resmi. Halamannya bergaya kertas buku dengan efek balik halaman, penjelasan lengkap, kotak rumus, contoh soal yang dibuka langkah demi langkah, "Awas jebakan", dan rangkuman. Ada juga penanda "lanjutkan membaca".
- **Peta jalur panjang ala Duolingo**: satu titik untuk tiap subbab, lalu latihan di akhir tiap bab, plus paket latihan dari bank soal. Titiknya berikon dan terbuka satu per satu.
- **Bank soal**: soal dari kisi-kisi dan contoh resmi, latihan TKA B. Inggris, dan Kompeten TKA. Formatnya PG, PG Kompleks (pilih lebih dari satu), dan PG Kompleks Kategori (tabel), lengkap dengan gambar yang bisa diperbesar. Setiap soal ada kunci dan pembahasannya.
- **Progres interaktif**: level, streak, cincin per mapel, grafik harian, lencana, peti hadiah, dan daftar soal salah.
- **Sesi Harian**: 5 jam belajar, lalu 1 jam soal, lalu foto atau ketik ringkasan. Selama sesi, aplikasi disematkan dan layar tetap menyala. Ada tombol darurat.
- **Pemblokir distraksi (Aksesibilitas)**: menutup aplikasi lain saat sesi berjalan. Ada opsi blokir terus dan batas harian.

## Sumber materi

Lingkup materi mengikuti kisi-kisi TKA SMA 2026 dari Pusmendik Kemendikdasmen, sebagaimana dikutip CNN Indonesia, Medcom, Kumparan, Bisadanedu, dan Blog ITERA. Daftar lengkapnya ada di tab Progres → Sumber materi.

## Build

GitHub Actions (`.github/workflows/android.yml`) menjalankan unit test, membangun APK, merilisnya sebagai `latest`, lalu menjalankan tes UI di emulator dan menyimpan screenshot QC di branch `qc-screenshots`.

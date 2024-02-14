# E-Shop

Repositori ini merupakan proyek untuk tugas mata kuliah _Advance Programming_ (Tutorial 1)

Nama    :  Ilham Abdillah Alhamdi <br>
NPM     : 2206081194

Link Web Server : https://advshop-elhamdi.koyeb.app/

Scanned by SonarCloud <br>
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ilhamelhamdi_advance-programming-tutorial-1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ilhamelhamdi_advance-programming-tutorial-1)

<details open>
<summary><h2>Modul 2</h2></summary>

### Code Quality Issue

1. SonarQube secret issue
   Terdapat issue dimana konfigurasi token sonarcloud ditulis langsung pada source code. Untuk mengatasi hal ini, token
   perlu disimpan dan diakses di environment variable.

### Implementasi _Continous Integration_ & _Continous Deployment_

Implementasi Github Workflow pada repositori ini telah memenuhi prinsip CI/CD. Pertama, dari sisi CI, kode pada proyek
ini telah menerapkan automasi script untuk melakukan verifikasi kode, seperti menjalankan _test script_ dan _code
quality analysis_. Pada proyek ini, test script diimplementasikan pada workflows `ci.yml` dan _code quality analysis_
diimplementasikan pada workflow `build.yml`. Selain itu, proyek ini juga telah mengimplementasikan Continous Deployment
dengan menerapkan automasi pada proses _delivery_ atau _deployment_. Proyek ini dideploy pada
PaaS [Koyeb](https://www.koyeb.com/) secara otomatis dengan menggunakan fitur autodeploy pada console Koyeb.

</details>

<details>
<summary><h2>Modul 1</h2></summary>

## Refleksi 1

Menulis program dalam Spring Boot, bagi saya, cukup challenging mengingat ini adalah pertama kali saya menggunakan
_framework_ Spring Boot. Pada exercise 1, saya telah mengimplementasi fitur *edit* dan *delete product* pada aplikasi.
Kode masing-masing fitur sudah diimplementasikan dengan penamaan variabel yang representatif. Misalnya
variabel `oldProduct`, `newProduct`, dan `deletedProduct` yang menggambarkan object product pada state yang berbeda.
Dengan penamaan variabel yang representatif, penggunaan komentar redundan pun dapat dihindari. Selain, fungsi dalam kode
tersebut juga sudah menerapkan prinsip "Do One Thing".

Namun, masih ada beberapa hal yang masih perlu diperbaiki lagi. Menurut saya, penulisan fungsi
seperti `public Product update(String productId, Product newProduct)` belum sepenuhnya menerapkan Clean Code. Jika
ditelaah dari signature fungsi tersebut, fungsi tersebut melakukan proses update (Command) dan juga mengembalikan
data `Product` (Query). Hal ini melanggar prinsip Command Query Separation. Selain itu, beberapa fungsi juga belum
menerapkan error-handling dengan baik. Misalnya pada fungsi delete, jika produk dengan id yang diberikan tidak
ditemukan, program akan crash karena tidak ada exception handling.

## Refleksi 2

1. Unit Test & Code Coverage

   Menurut saya, penulisan unit test cukup dapat memberikan _confidence_ bahwa kode yang kita tulis sudah sesuai
   _requirement checklist_ dan minim dari bugs. Seberapa banyak unit test untuk sebuah class? Menurut saya, jumlah unit
   test yang dibuat dapat disesuaikan dengan _requirement checklist_ untuk fungsi tersebut, termasuk skenario positif
   dan negatifnya. Nah, menurut saya dari prinsip, kita bisa membalik kondisinya. Dari yang awalnya dilakukan proses
   penulisan kode kemudian penulisan unit test, menjadi unit test terlebih dahulu kemudian kode program. Proses ini
   sesuai dengan prinsip Test-Driven Development yang mendahulukan penulisan test sebelum kode sebenarnya. Dengan
   mengikuti prinsip ini, unit test yang kita tulis akan sesuai dengan kebutuhan program.

   Adapun terkait dengan code coverage, menurut saya hal ini kurang dapat merepresentasikan kualitas kode yang kita
   tulis. Misalkan kita memiliki fungsi yang tidak menerapkan exception-handling tertentu. Jika tidak ada unit test yang
   menjalankan skenario yang menyebabkan exception tersebut, code coverage yang diperoleh bisa saja mencapai
   100% karena seluruh baris kode dijalankan. Padahal pada kenyataannya, program tersebut dimungkinkan mengalami bug
   akibat exception tersebut.


2. Penulisan Functional Test yang Mirip

   Menuliskan functional test yang baru dapat meningkatkan kualitas kode jika meng-cover lebih banyak skenario. Pada hal
   ini, termasuk juga menuliskan funtional test untuk mengecek jumlah produk yang telah ditambahkan. Namun menuliskan
   test tersebut sebagai class baru dengan konfigurasi setup dan instance variable yang sama dengan suatu functional
   test lain, mungkin bukan praktik yang ideal. Terkecuali apabila terdapat alasan _separation concern_ lain, membuat
   fungsi yang mirip dengan fungsi lain justru akan menimbulkan masalah duplikasi kode. Hal ini melanggar prinsip "Don't
   Repeat Yourself (DRY)". Selain itu, fungsi mengecek jumlah produk juga memiliki potensi masalah clean code dalam hal
   _coupling_/ketergantungan dengan kode lain, seperti fungsi create dan delete. Sebaiknya test untuk mengecek jumlah
   produk dapat digabung dalam functional test untuk fitur create dan delete.

</details>
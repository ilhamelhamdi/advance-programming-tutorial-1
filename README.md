# E-Shop

Repositori ini merupakan proyek untuk tugas mata kuliah _Advance Programming_ (Tutorial 1)

Nama    :  Ilham Abdillah Alhamdi <br>
NPM     : 2206081194

Link Web Server : https://advshop-elhamdi.koyeb.app/

Scanned by SonarCloud <br>
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ilhamelhamdi_advance-programming-tutorial-1&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ilhamelhamdi_advance-programming-tutorial-1)

<details open>
<summary><h2>Modul 3</h2></summary>

### Implementasi SOLID Principle

1. Single Responsibility Principle
    - Menggunakan arsitektur MVC dengan SpringBoot. Memisahkan penangangan http pada controller, objek pada model,
      penyimpanan data pada repository, dan pengolahan business logic pada service.
    - Memisahkan CarController dari ProductController.java.
    - Memisahkan pembuatan id dari repository & controller ke model layer
2. Open-Closed Principle
    - Mengekstrak fungsi-fungsi CRUD pada service ke sebuah interface sehingga memungkinkan penambahan
3. Liskov Substitution Principle
    - Membuat relasi antara model Product dan Car sebagai inheritance. Hal ini mengingat bahwa model Car memiliki
      atribut-atribut yang dapat diwariskan dari model Car.
4. Interface Segregation Principle
    - Mengekstrak fungsi-fungsi CRUD yang hanya digunakan bersama-sama oleh kedua service ke CRUDService interface
5. Dependency Injection Principle
    - Menggunakan tipe interface sebagai dependensi pada service-to-repo, controller-to-repo, dan injeksi pada test.

Note : Sebenarnya, ada beberapa poin action di atas yang tidak hanya mengimplementasikan satu prinsip saja, namun
beberapa prinsip SOLID sekaligus.

### Keuntungan Implementasi SOLID Principles

1. Maintainability

   SOLID Principle membuat code menjadi lebih clean, modular, dan setiap module memiliki tugas yang jelas. Dengan
   demikian, codebase akan lebih mudah di-maintain. Contohnya seperti dalam hal menemukan bug yang lebih mudah. Hal
   karena unit kode (function/class) dalam SOLID memiliki ukuran lebih kecil & atomik.

2. Flexibility & Extensibility

   SOLID Principle mendorong penulisan kode yang lebih fleksible dan mudah dipahami. Secara spesifik, Open-Closed
   Principle (OCP) dan Liskov Substition Principle (LSP) memungkinkan penambahan fitur tanpa memodifikasi kode yang
   sudah ada. Misalnya, apabila ingin ditambahkan repository yang berinteraksi secara remote (misal.
   ProductRemoteRepository), kita hanya perlu menambahkan class tersebut tanpa mengubah class lain yang bergantung
   padanya. Hal ini karena dependensi pada class tersebut bergantung pada interface, bukan class impelemtasi.

4. Readability 

   Single Responsibility Principle (SRP) mendorong penulisan kode menjadi sekecil mungkin, terfokus, dan memiliki tugas
   yang jelas.

4. Testability

   Karena SOLID Principle mendorong penulisan kode yang modular, atomik, dan loose-coupled, penulisan kode test juga
   akan lebih mudah.

5. Kolaborasi

   Pemisahan kepentingan pada SOLID principle dapat membantu proses kerja tim lebih cepat dan efisien.



### Kekurangan Tidak Mengimplementasikan SOLID Principles

1. Rigidity

   Tanpa mengimplementasikan Open-Closed Princple (OCP), penambahan atau perubahan pada fitur akan lebih sulit
   dilakukan. Hal ini karena penambahan fitur memerlukan perubahan pada kode yang sudah ada. Terkadang, jumlah kode yang
   perlu diubah tidak sedikit karena kode antarmodul terhubung kuat satu sama lain. Contohnya, apabila kita ingin
   menambahkan ProductRemoteRepository untuk mendukung pengaksesan data secara remote, kita perlu mengubah beberapa kode
   di service layer agar dapat menggunakan repository yang baru.

2. Fragility

   Kode menjadi rentan terkena bug jika tidak mengimplementasikan SOLID Principle dengan baik. Perubahan pada suatu
   modul dapat mengubah behaviour pada modul lain. Hal ini dapat mengakibatkan efek domino.

3. Sulit Debugging

   Violation pada SRP membuat suatu class/function menanggung banyak tugas. Hal ini membuat kode lebih sulit untuk
   dipahami dan di-debug. Contohnya, kode pembuatan id model ditulis pada layer controller. Menurut konvensi, layer
   controller berfungsi untuk menangani request dan response HTTP dan tidak ada hubungannya dengan pengubahan field
   suatu objek. Apabila kode pembuat id tersebut terdapat bug, kita tidak dapat secara langsung mengetahui dimana bug
   tersebut berada karena lokasinya yang tidak sesuai.

4. Duplikasi Kode

   Tanpa mengikuti SOLID Principle, kemungkinan kode yang ditulis memiliki banyak duplikasi. Kode yang terduplikasi ini
   tidak terenkapsulasi dengan baik dengan prinsip OOP, seperti inheritance. 


</details>

<details>
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
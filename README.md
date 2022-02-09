# Glisemik İndeks Android Uygulaması

Bu uygulama günlük hayatta yaygın olarak kullanılan besinleri, yanlarında Glisemik İndeks, Kalori ve Karbonhidrat değerleri ile birlikte kullanıcıya sunar. Uygulamanın ana hedef kitlesi olan şeker hastalığına (diyabet) sahip kullanıcılar, tüketmek istedikleri besinlerin bilgilerini kolayca uygulama üzerinden öğrenebilecekler. Bu sayede hem sağlıklı kalıp hem de yemek istedikleri besinler konusunda bilgilenmiş olacaklar. 

## Splash Ekranı
Uygulama Splash ekranı sırasında, içinde tutacağı verileri <b>html parsing</b> yaparak toplar ve database'e kaydeder. Kullanıcının sonraki girişlerinde veriler direkt olarak bu database üzerinden gelir ve kullanıcı splash ekranı tekrar görmez. Kullanıcı verileri sıfırlamak isterse ayarlara girerek verileri sıfırlayabilir. Sıfırlama sonrası uygulama tekrar çalışarak yine Splash ekranında verileri alır ve tekrar database'e kaydeder.

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/1_splash.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/1_splash.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/15_reset_db.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/15_reset_db.png" width="200" style="max-width:100%;"></a>

## Ana Ekran ve Besin Listesi
Uygulama direkt olarak besin listesi bölümünde açılır. Burada veriler, siteden alındığı sırayla kullanıcıya gösterilir. Görselliği zenginleştirmek için liste, glisemik İndeks değerine göre 3 farklı renkte arka plan almaktadır. Listedeki besinlerin üzerine basınca besin düzenleme ekranı açılır, listedeki besinlerin üzerine uzun basınca da silme ekranı açılır. Bu ana ekran üstte arama özelliği ve altta da filtreleme butonuna sahiptir. 

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/2_home.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/2_home.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/6_delete_food.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/6_delete_food.png" width="200" style="max-width:100%;"></a>

## Besin Düzenleme/Güncelleme Ekranı
Besin listesi ekranında bir besin üzerine basınca bu besin güncelleme ekranı açılır. Buradan tüm besin değerleri değiştirilebilir. Besinin adı, glisemik indeks değeri, karbondihdrat değer, kalori değeri ve ait olduğu tablo/kategori de buradan değiştirilebilir. Tablolar bir açılır liste ile gelir ve besinin ait olduğu tablo en başta seçili durumda olur. Boşluk kontrolleri yapıldıktan sonra kullanıcıya onay için sorulur ve kullanıcı onayı sonrası, besin database üzerinde güncellenir. Listeye tekrar dönüldüğünde besin değerleri güncellenmiş olur ve besin değerine göre renk değişimi de gerçekleşir.

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/7_edit_food.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/7_edit_food.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/8_edit_food.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/8_edit_food.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/9_edit_result.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/9_edit_result.png" width="200" style="max-width:100%;"></a>

## Filtreleme
Besin listesinde altta yer alan filtreleme butonuna basılınca tüm tablo isimlerinin yer aldığı bir liste önümüze çıkar. Bu listeye ek olarak istenen aralığı girmek için 2 tane de metin kutusu yer alır. Kullanıcı bu metin kutularını boş bırakırsa standart değer döner. İstenen değerler girildiğinde uygula butonuna basılır ve istenen değerler ekrana yansıtılır.

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/3_filter.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/3_filter.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/4_filter.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/4_filter.png" width="200" style="max-width:100%;"></a>

## Arama
Arama özelliği de arama kutusuna yazılan girdiyi veritabanında arar, bu girdiyi içeren tüm değerler ekrana yansıtılır. Bu girdi besin adı olabileceği gibi diğer değerler de olabilir. Girdi string, int veya float bir değerde de olabilir.

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/5_search.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/5_search.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/5_search_number.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/5_search_number.png" width="200" style="max-width:100%;"></a>

## Besin ve Tablo Ekleme
Uygulamanın sol tarafındaki menüden besin ekleme ve tablo düzenleme sekmelerine ulaşılabilir. Bu sekmelerden besin düzenleme yapısına benzer bir yapıda besin eklemesi yapılır. Tablo düzenleme ekranında ise mevcut tablolar görüntülenir ve üstten tablo eklemesi yapılabilir.

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/10_add_food.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/10_add_food.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/11_tables.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/11_tables.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/16_navbar.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/16_navbar.png" width="200" style="max-width:100%;"></a>

## Tablo Düzenleme
Besin düzenleme yapısına benzer şekilde tablolar da düzenlenebilir. Listeye uzun basınca silme işlemi, kısa basınca da düzenleme ekranı açılır. Düzenlenen tablonun yeni bilgileri hemen listeye yansır.

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/12_delete_table.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/12_delete_table.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/13_edit_table.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/13_edit_table.png" width="200" style="max-width:100%;"></a>

<a href="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/14_edit_table.png" target="_blank">
<img src="https://github.com/yemregul94/Android-Glycemic-Index/blob/main/screenshots/14_edit_table.png" width="200" style="max-width:100%;"></a>



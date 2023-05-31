# Unit-test-And-Spring-Security-Project
An example API that has undergone unit testing and utilizes Spring Security for its security measures.   


# How to Unit Test
# 1.
İlk önce bize gerekli olan classların Mock'unu oluşturuyoruz.

->Bunu setup() diye bir fonksiyon içinde yapabiliriz.
->(accountRepository = Mockito.mock(AccountRepository.class))
-->@BeforeEach anatasyonu ekleyerek.

yada  böylede yapılabilir

->@Mock
  private AccountRepository accountRepository;

->Aynı zamanda ana service class'ı mock etmiyoruz fakat
setup() fonksiyonu içinde oluşturuyoruz ve mocklanmış class'ları veriyoruz.


# 2.
Fonksiyon şeklinde kullanıcılar oluşturabiliriz(Ve onların dtolarını).
Bu bize kolaylık sağlar.
->Yani bize gerekli olan Data'ları yazıyoruz.

# 3.
Mockito.when(accountRepository.getAccountById("123")).thenReturn(account)
->Böylece kodlar kısmında yazan fonksiyonları çağırırız.
->Her metod için when yazılır

# 4.
AccountDto result = accountService.createAccount(createAccountDto);

# 5.
Assertions.assertEquals(result, accountDto);
-> Ressult ile kendi oluşturduğumuz accountDto ile kıyaslar aynı ise true döndürür
	Farklı ise hata fırlatır

# 6.
Mockito.verify(accountRepository).save(account);
->Burda bu fonksiyonu çağırıp çağırmadığını doğrular.


Test
->public olmalı
->void dönmeli


<br>1-Test ismi yazılır        //<br>
<br>2-Test verileri hazırlanır       // Data
<br>3-Bağmlı service'lerin davranışları belirlenir  // mockito.when(...).thenReturn()
<br>4-Test edilecek metod çalıştırılır		// result = service.function(data)
<br>5-Test sonuçları karşılaştırılır		// Assertions.assertEquals(result,data)
<br>6-Bağımlı service'lerin çalıştırılması kontrol edilir// mockito.verify(service/repository/dtoConverter).function()


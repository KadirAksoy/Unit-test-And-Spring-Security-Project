# Unit-test-And-Spring-Security-Project
An example API that has undergone unit testing and utilizes Spring Security for its security measures.   


# Unit Test nasıl yapılır ?
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


# Test
->public olmalı
->void dönmeli


<br>1-Test ismi yazılır        //<br>
<br>2-Test verileri hazırlanır       // Data
<br>3-Bağmlı service'lerin davranışları belirlenir  // mockito.when(...).thenReturn()
<br>4-Test edilecek metod çalıştırılır		// result = service.function(data)
<br>5-Test sonuçları karşılaştırılır		// Assertions.assertEquals(result,data)
<br>6-Bağımlı service'lerin çalıştırılması kontrol edilir// mockito.verify(service/repository/dtoConverter).function()

# Security İşlemleri nasıl yapılır ?

# 1.
UserDetailsServiceImp extend UserDetailsService şeklinde class oluştururuz
->loadUserByUsername
--><br>sağlanan kullanıcı adına göre UserRepository'den bir kullanıcı alır.
   <br>Kullanıcı bulunamazsa, bir usernameNotFoundException atar.
   <br>Kullanıcı bulunursa, bir UserDetails nesnesi oluşturulur ve döndürülür.

# 2.
JwtProvider class'ımız olmalı bu class JSON Web Token işlemlerini içerir.
->generateToke
--><br>Bu kod, bir kullanıcı için bir JWT (JSON Web Token) oluşturmayı amaçlar.

->getUsernameFromJWT
--><br>Bu kod, kullanıcı adını bir JSON Web Tokeninden (JWT) alır.

->validateToken
--><br>Bu kod, belirli bir JWT'nin gerçekliğini doğrular.

# 3.
JwtAuthenticationFilter bu class filtreleme işlemlerini gerçekleştirir.
->doFilterInternal
--><br>Bu kod, Spring Security filtrelerinden biri olan doFilterInternal yöntemini içerir.
   <br>Bu metot, HTTP isteklerinin işlenmesi sırasında çalışan bir filtre işlevi görür.

->getJWTFromRequest
--><br>Bu kod, JWT'yi bir HTTP isteğinden alır.
   <br>Bir HttpServletRequest nesnesi kullanılarak istekte bulunan JWT'yi elde etmek için kullanılır

# 4.
JwtAuthEntryPoint bu class kimlik doğrulama başarısız olduğunda kullanılır.
->commence
--><br>Bu yöntem, kimlik doğrulama başarısız olduğunda yürütülür ve
   <br>yetkilendirme gerektiren korumalı bir kaynağa erişim isteniyor.	

# 5.
SecurityConfig bu class configurasyon işlemlerini yapmak için kullanılır.
->securityFilterChain
-->Bu kod bir Spring Security yapılandırma sınıfında yer alır ve 
Spring Security'nin HTTP isteklerini nasıl filtreleyeceğini ve yetkilendireceğini belirtir. 
 
->authenticationManager
-->AuthenticationManager, kimlik doğrulama işlemlerini yöneten bir Spring Security bileşenidir.

->passwordEncoder
--><br>BCrypt algoritmasını kullanarak şifreleri özetleyen ve doğrulayan BCryptPasswordEncoder sınıfının bir örneğini döndürür.
   <br>Bu, kullanıcı parolalarının güvenli bir şekilde saklanmasını ve doğrulanmasını sağlar.

->jwtAuthenticationFilter
--><br>JwtAuthenticationFilter, JWT kimlik doğrulaması için gelen istekleri işleyen bir Spring Security filtresidir,
   <br>ve kimlik doğrulama başarılı olursa, güvenlik bağlamını kimliği doğrulanmış kimlikle doldurur.
   <br>Bu filtreyi güvenlik yapılandırmasına ekleyerek JWT tabanlı kimlik doğrulamayı etkinleştirir.

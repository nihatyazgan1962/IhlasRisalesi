package com.ihlasrisalesi.analiz.data

data class SyncedParagraph(
    val index: Int,
    val title: String,
    val subtitle: String,
    val startMs: Int,
    val endMs: Int,
    val originalText: String,
    val modernExplanation: String,
    val keyThought: String
) {
    val startSeconds: Int get() = startMs / 1000
    val endSeconds: Int get() = endMs / 1000
}

/**
 * İhlas Risalesi (21. Lem'a) - İhsan Atasoy Orijinal Seslendirme Senkronizasyonu
 * Toplam Süre: 28 dakika 06 saniye (1.686.880 ms)
 * Seslendiren: İhsan Atasoy
 */
object AudioSyncData {
    val paragraphs: List<SyncedParagraph> = listOf(
        SyncedParagraph(
            index = 0,
            title = "İhtar ve Mukaddime",
            subtitle = "Bu Lem'a lâakal her on beş günde bir defa okunmalı",
            startMs = 0,
            endMs = 76000,
            originalText = """Lem'alar, Yirmi Birinci Lem'a, İhlas Hakkında. Bu Lem'a lâakal her on beş günde bir defa okunmalı. Bismillâhirrahmânirrahîm. Ve lâ tenâzeû fetefşelû ve tezhebe rîhukum. Ey âhiret kardeşlerim ve ey hizmet-i Kur'âniyede arkadaşlarım! Bilirsiniz ve biliniz: Bu dünyada, hususan uhrevî hizmetlerde en mühim bir esas, en büyük bir kuvvet, en makbul bir şefaatçi, en metin bir nokta-i istinad, en kısa bir tarîk-i hakikat, en makbul bir dua-yı mânevî, en kerametli bir vesile-i makasıd, en âli bir haslet, en safi bir ubudiyet: İHLÂSTIR.""",
            modernExplanation = "İhlas Risalesi'nin mukaddimesi. İhlasın manevi hizmetlerde en büyük dayanak, tükenmez bir kuvvet ve duaların kabul vesilesi olduğu bildiriliyor.",
            keyThought = "İhlas amelin ruhudur; ihlassız amel meyvesiz kalır."
        ),
        SyncedParagraph(
            index = 1,
            title = "Zamanın Dehşeti ve İhlas İhtiyacı",
            subtitle = "Neden İhlâsa Mecburuz?",
            startMs = 76000,
            endMs = 210000,
            originalText = """Madem ihlasta mezkûr hassalar gibi çok envâr bulunur ve pek çok kuvvetler var. Ve madem bu müthiş zamanda ve dehşetli düşmanlar mukabilinde ve şiddetli tazyikat karşısında ve savletli bid'alar, dalâletler hücumunda pek az ve zaîf ve fakir ve kuvvetsiz olduğumuz halde, gayet ağır ve büyük ve umumî ve kudsî bir vazife-i imaniye ve hizmet-i Kur'âniye omuzumuza ihsan-ı İlâhî tarafından konulmuş; elbette herkesten ziyade bütün kuvvetimizle ihlası kazanmaya mecbur ve mükellefiz. Ve ihlasın sırrını kendimizde yerleştirmeye pek çok muhtacız. Yoksa hem şimdiye kadar kazandığımız hizmet-i kudsiyye kısmen zayi olur, devam etmez; hem şiddetli mesul oluruz. Ey kardeşlerim! Mühim ve büyük bir umûr-ı hayriyenin çok muzır mânileri olur. Şeytanlar o hizmetin hadimleriyle çok uğraşır. Bu mânilere ve şeytanlara karşı ihlas kuvvetine dayanmak gerektir. İhlası kıracak esbabdan yılandan, akrepten çekindiğiniz gibi çekininiz. Hazret-i Yûsuf Aleyhisselâm 'İnne'n-nefse le-emmâretün bi's-sû'i illâ mâ rahime Rabbî' demesiyle nefs-i emmareye itimat edilmez. İhlası kaçırmamak ve bu mânileri def'etmek için gelecek düsturlar rehberiniz olsun.""",
            modernExplanation = "Müminler az ve zayıf olsalar dahi, samimiyet ve ihlas ile dağ gibi engelleri aşabilirler. İhlas bozulursa geçmiş hizmetler dahi zayi olabilir.",
            keyThought = "Azlık ve zayıflığın tek manevi ilacı ve zırhı ihlastır."
        ),
        SyncedParagraph(
            index = 2,
            title = "Birinci Düstur: Rıza-yı İlâhî",
            subtitle = "Amelinizde Yalnız Allah Rızası Olmalı",
            startMs = 210000,
            endMs = 246000,
            originalText = """BİRİNCİ DÜSTURUNUZ: Amelinizde rıza-yı İlâhî olmalı. Eğer O razı olsa, bütün dünya küsse ehemmiyeti yok. Eğer O kabul etse, bütün halk reddetse tesiri yok. O razı olduktan ve kabul ettikten sonra, isterse ve hikmeti iktiza ederse, sizler istemek talebinde olmadığınız halde, halklara da kabul ettirir, onları da razı eder. Onun için, bu hizmette doğrudan doğruya yalnız Cenab-ı Hakk'ın rızasını esas maksad yapmak gerektir.""",
            modernExplanation = "Hedef insanların alkışı veya beğenisi değil, yalnızca Allah'ın hoşnutluğudur. Allah razı olduktan sonra dilerse kalpleri de size meylettirir.",
            keyThought = "Halkın teveccühü aranmaz, Allah'ın rızası esas maksattır."
        ),
        SyncedParagraph(
            index = 3,
            title = "İkinci Düstur: Kardeşleri Tenkit Etmemek",
            subtitle = "Faziletfuruşluk ve Gıbta Damarı",
            startMs = 246000,
            endMs = 286000,
            originalText = """İKİNCİ DÜSTURUNUZ: Bu hizmet-i Kur'âniyede bulunan kardeşlerinizi tenkid etmemek ve onların üstünde faziletfuruşluk nev'inden gıbta damarını tahrik etmemektir. Çünki nasıl insanın bir eli diğer eline rekabet etmez, bir gözü bir gözünü tenkid etmez, dili kulağına itiraz etmez, kalb ruhun ayıbını görmez... Belki birbirinin noksanını ikmal eder, kusurunu örter, ihtiyacına yardım eder, vazifesine muavenet eder. Yoksa o vücud-u insanın hayatı söner, ruhu kaçar, cismi de dağılır.""",
            modernExplanation = "Mümin kardeşler bir vücudun uzuvları gibidir. El ele rekabet etmez, göz kulağı tenkit etmez. Birlik ve uhuvvet hayatın devam şartıdır.",
            keyThought = "Kardeşini tenkit eden, kendi vücudunun bir uzvunu yaralar."
        ),
        SyncedParagraph(
            index = 4,
            title = "İkinci Düstur: Fabrika Çarkları Temsili",
            subtitle = "Ahenk ve Ortak Gaye",
            startMs = 286000,
            endMs = 476000,
            originalText = """Hem nasılki bir fabrikanın çarkları birbiriyle rekabetkârane uğraşmaz, birbirinin önüne tekaddüm edip tahakküm etmez, birbirinin kusurunu görüp tenkid ederek sa'ye hevesini kırıp atalete uğratmaz. Belki bütün istidadlarıyla birbirinin hareketini umumî maksada tevcih etmek için yardım ederler, hakikî bir tesanüd bir ittifak ile gaye-i hilkatlerine yürürler. Eğer zerre mikdar bir taadî, bir tecavüz başgösterse; o fabrikayı hercümerc eder, neticesiz akîm bırakır. Fabrika sahibi de o fabrikayı bütün bütün kırıp dağıtır. İşte ey Risale-i Nur şakirdleri ve Kur'an'ın hizmetkârları! Sizler ve bizler öyle bir insan-ı kâmil ismine lâyık bir şahs-ı manevînin âzalarıyız ve saadet-i ebediye haritasında çalışan makinelerin çarkları hükmündeyiz ve Cenab-ı Hakk'ın rızasını tahsil yolunda vazifedarız. Elbette birbiriyle rekabete, tenkide ve gıbtaya asla meydan verilmemelidir.""",
            modernExplanation = "Fabrikadaki çarkların her biri ayrı bir vazife görür. Biri diğerine engel olursa fabrika durur. Kardeşler birbirinin şevkini kırmamalıdır.",
            keyThought = "Rekabet değil ahenk; tenkit değil yardımlaşma."
        ),
        SyncedParagraph(
            index = 5,
            title = "Üçüncü Düstur: Kuvveti İhlas ve Hakta Bilmek",
            subtitle = "111 Sırrı ve Hakiki Tesanüd",
            startMs = 476000,
            endMs = 650000,
            originalText = """ÜÇÜNCÜ DÜSTURUNUZ: Bütün kuvvetinizi ihlasta ve hakta bilmelisiniz. Evet, kuvvet haktadır ve ihlastadır. Haksızlar dahi, haksızlıkları içinde gösterdikleri ihlas ve samimiyet yüzünden kuvvet kazanıyorlar. Evet, sırr-ı ihlas ile cilvelenen hakikî bir tesanüd; üç elifin (1 1 1) ittihadıyla yüz on bir kıymetini aldığı gibi, hakikî üç hâdim-i din yan yana gelse, yüz on bir kuvvetinde bir şahs-ı manevî teşkil eder. Dörde dört elif (1 1 1 1) olsa bin yüz on bir kuvvetini verir. Nasılki on altı adamın ayrı ayrı çalışması on altı adam kuvvetindedir; fakat sırr-ı uhuvvet ve müşterek gaye ile çalışan on altı adam, bin yüz on bir kuvvetinde bir şahs-ı manevî tecelli ettirir. Çünki hakikî ihlaslı bir hizmette enaniyet kalkar, 'ben' gider 'biz' gelir. Herkes kardeşinin gözüyle bakar, kulağıyla işitir, aklıyla düşünür.""",
            modernExplanation = "Üç tane 1 alt alta toplanırsa 3 eder; fakat omuz omuza verip yan yana dizilirse 111 olur. İhlaslı birliktelik gücü katbekat katlar.",
            keyThought = "111 Sırrı: Samimi birlik kemiyeti keyfiyete dönüştürür."
        ),
        SyncedParagraph(
            index = 6,
            title = "Dördüncü Düstur: Fena fi'l-İhvan ve Tefani",
            subtitle = "Kardeşlerin Meziyetleriyle İftihar Etmek",
            startMs = 650000,
            endMs = 765000,
            originalText = """DÖRDÜNCÜ DÜSTURUNUZ: Kardeşlerinizin meziyetlerini şahıslarınızda ve faziletlerini kendinizde tasavvur edip, onların şerefleriyle şâkirane iftihar etmektir. Ehl-i tasavvufun mabeyninde 'fena fiş-şeyh', 'fena fir-resul' tabir edilen bir mertebe-i kemalât vardır. Ben de o meslek-i âlinin bir nev'ini mesleğimizde görüyorum ki; 'fena fi'l-ihvan' namını veriyorum. Yani, kardeşleri içinde fani olmak; yani, kendi enaniyetini onların meziyetleri içinde eritmek, onların meziyetleriyle sevinmek, onların şerefiyle iftihar etmektir. Bir kimse meselâ bir çeşmeden su içse, o suyun lezzetini kendi ağzında hissettiği gibi; kardeşinin ilmiyle, irfanıyla, takvasıyla, güzel hitabetiyle hasıl olan feyzi kendi hissesi bilip Allah'a şükretmelidir. Bu hal, hasedi kökünden keser, gıybeti men eder, muhabbeti zevalsiz kılar.""",
            modernExplanation = "Kardeşinde gördüğün güzel bir ahlak veya meziyet karşısında kıskançlık değil, o meziyet sende varmış gibi sevinip şükretmektir.",
            keyThought = "Tefani: Kardeşinin şerefiyle kendi şerefin gibi iftihar etmektir."
        ),
        SyncedParagraph(
            index = 7,
            title = "İhlası Kazandıran İki Müessir Çare",
            subtitle = "Râbıta-i Mevt ve Huzur-u Daimî",
            startMs = 765000,
            endMs = 941000,
            originalText = """İHLASI KAZANDIRAN VE MUHAFAZA EDEN İKİ ÇARE: Birinci Çare: RÂBITA-I MEVT'TİR. Ölümü düşünmektir. Tûl-i emeli kırar. Kendi cenazesine, asrının ölümüne ve dünyanın fena bulacağına hakikat gözüyle bakan nefis, fani dünyanın sahte alkışlarına aldanmaz. Hadîs-i şerifte 'Lezzetleri tahrip edip acılaştıran ölümü çok zikrediniz' buyrulmuştur. İkinci Çare: İMAN-I TAHKİKÎ VE MARİFETULLAH İLE HUZUR-U DAİMÎ KAZANMAKTIR. Her an Cenab-ı Hakk'ın huzurunda olduğunu bilmektir. Padişahın huzurundaki adam etraftaki hizmetkârlara gösteriş yapmaz. Hakiki huzur şuuru riyayı kökünden siler.""",
            modernExplanation = "Ölümü hatırlamak (râbıta-i mevt) nefsin bitmek bilmez hırslarını dindirir. Tahkiki iman ve huzur-u daimi ise kulun her an Allah'ın huzurunda olduğu idrakini verir.",
            keyThought = "Ölümü düşünen riyadan kaçar; Allah'ın huzurunu hisseden ihlası bulur."
        ),
        SyncedParagraph(
            index = 8,
            title = "İhlası Kıran Birinci Mâni: Maddi Menfaat",
            subtitle = "Menfaat Hırsı ve Rekabetin İlâcı: Kanaat",
            startMs = 941000,
            endMs = 1280000,
            originalText = """İHLASI KIRAN VE RİYAYA SEVK EDEN MÂNİLER: Birinci Mâni: Menfaat-i maddiye cihetinden gelen rekabet ve hırs. Ehl-i dünya nasıl menfaat-i maddiye için yarışırlar; bazen ehl-i din dahi o hırsa kapılıp birbirine rekabet eder. Halbuki rızık taahhüd-ü Rabbanî altındadır. Rızk-ı helal kanaattedir. Kanaat eden, hırsın zilletinden ve rekabetin zehrinden kurtulur. Cenab-ı Hakk'ın rızıkta koyduğu bereket sırrına itimat etmek gerektir.""",
            modernExplanation = "Dünyalık menfaat peşinde koşmak samimiyeti zedeler. Çaresi rızkın Allah'ın teminatı altında olduğuna inanmak ve kanaat kalesine sığınmaktır.",
            keyThought = "Kanaat tükenmez bir hazinedir; hırs ise zillet sebebidir."
        ),
        SyncedParagraph(
            index = 9,
            title = "İkinci ve Üçüncü Mâni: Hubb-u Câh ve Korku",
            subtitle = "Şan-Şöhret Sevgisi ve Tevekkül Zırhı",
            startMs = 1280000,
            endMs = 1500000,
            originalText = """İkinci Mâni: Hubb-u câh ve şan-şöhret sevgisi. İnsanların teveccühünü aramak ihlası kaçırır, ameli mahveder. Kabir kapısında hükümsüz bir sevgidir. Bu hamur çok su götürür; nefis daima kendini göstermek ve takdir edilmek ister. Çaresi: Teveccüh-ü nâsın geçici, ameli ibtal edici ve kabirde faydasız olduğunu bilmektir. Üçüncü Mâni: Korku ve tama'. Ehl-i dalaletin tehdidinden veya fakirlikten korkup haktan vazgeçmek. Hakiki tevekkül bu korkuları izale eder; zira ecel birdir, tebeddül etmez.""",
            modernExplanation = "Şöhret arzusu gizli riyadır. Korku ise nefsin zaafıdır. Çareleri ecelin bir olduğuna ve Allah'ın hıfzına teslimiyettir.",
            keyThought = "Hubb-u câh zehirli baldır; hakiki tevekkül bütün korkuları izale eder."
        ),
        SyncedParagraph(
            index = 10,
            title = "Hâtime: İhlas ve Sadakat Mektubu",
            subtitle = "Tevafuk ve Nurların Kerameti",
            startMs = 1500000,
            endMs = 1686880,
            originalText = """Bir kısım kardeşlerime hususi bir mektuptur: Aziz, sıddık kardeşlerim! İhlas Risalesi'nin başında 'Bu Lem'a lâakal her on beş günde bir defa okunmalı' ihtarının ehemmiyeti meydandadır. Kalblerinizi her türlü nifak, şikak, rekabet ve hasetten muhafaza ediniz. Nurların manevi bereketi ve Kur'an'ın feyziyle ihlas dairesinde sebat edip çalışanlar iki cihanda aziz olurlar. Cenab-ı Hak bizleri sırr-ı ihlasa mazhar eylesin. Âmin.""",
            modernExplanation = "İhlas Risalesi'nin hatimesi. Kardeşlik bağlarını haset ve nifaktan korumanın, ihlas dairesinde sebat etmenin dünyevi ve uhrevi bereketi beyan ediliyor.",
            keyThought = "Sebat, sadakat ve ihlas iki cihan saadetinin anahtarıdır."
        )
    )
}

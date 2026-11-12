package com.ihlasrisalesi.analiz.data

object IhlasDataProvider {

    val sections: List<SectionItem> = listOf(
        // 1. GİRİŞ VE İHTAR
        SectionItem(
            id = "giris_ihtar",
            title = "İhtar ve Mukaddime",
            subtitle = "Bu Lem'a lâakal her on beş günde bir defa okunmalı",
            category = CategoryType.GIRIS,
            originalText = """بِسْمِ اللّٰهِ الرَّحْمٰنِ الرَّح۪يمِ
وَلَا تَنَازَعُوا فَتَفْشَلُوا وَتَذْهَبَ ر۪يحُكُمْ

Bu Lem'a lâakal her on beş günde bir defa okunmalı.

Ey âhiret kardeşlerim ve ey hizmet-i Kur'âniyede arkadaşlarım!
Bilirsiniz ve biliniz: Bu dünyada, hususan uhrevî hizmetlerde en mühim bir esas, en büyük bir kuvvet, en makbul bir şefaatçi, en metin bir nokta-i istinad, en kısa bir tarîk-i hakikat, en makbul bir dua-yı mânevî, en kerametli bir vesile-i makasıd, en âli bir haslet, en safi bir ubudiyet: İHLÂSTIR.

Madem ihlasta mezkûr hassalar gibi çok envâr bulunur; ve madem bu müthiş zamanda ve dehşetli düşmanlar mukabilinde ve şiddetli tazyikat karşısında ve savletli bid'alar, dalâletler hücumunda pek az ve zaîf ve fakir ve kuvvetsiz olduğumuz halde, gayet ağır ve büyük ve umumî ve kudsî bir vazife-i imaniye ve hizmet-i Kur'âniye omuzumuza ihsan-ı İlâhî tarafından konulmuş; elbette herkesten ziyade bütün kuvvetimizle ihlası kazanmaya mecbur ve mükellefiz. Ve ihlasın sırrını kendimizde yerleştirmeye pek çok muhtacız. Yoksa hem şimdiye kadar kazandığımız hizmet-i kudsiyye kısmen zayi olur, devam etmez; hem şiddetli mesul oluruz.""",
            modernExplanation = """Bediüzzaman Hazretleri bu risaleye son derece hayati bir ikazla başlar: "Bu risale en az her 15 günde bir okunmalıdır." Çünkü nefis, dünya meşgalesi, benlik ve gizli riya insanı fark ettirmeden samimiyetten uzaklaştırabilir. 

İhlas; bir müminin amellerinde sadece ve sadece Allah'ın rızasını gözetmesidir. Uhrevi hizmetlerde ihlas; en büyük manevi dayanak, en kısa hakikat yolu ve duaların kabulüne en büyük vesiledir. Ehl-i dalaletin ve dünyanın hücum ettiği bir çağda, az sayıdaki inananların tek sığınağı ve muvaffakiyet sırrı ihlastır. İhlas kaybedilirse, yapılan bütün manevi hizmetler heba olma tehlikesiyle karşı karşıya kalır.""",
            keyLessons = listOf(
                "İhlas risalesi en az 15 günde bir okunarak niyetler tazelenmelidir.",
                "İhlas amelin ruhudur; ihlassız amel cesetsiz ruh gibidir, kıymetsizdir.",
                "Zor şartlarda ve az sayıda olunduğunda tek kuvvet kaynağı samimiyet ve ihlastır.",
                "İhlas zedelenirse geçmişteki hizmetler dahi zayi olabilir ve büyük manevi vebal doğar."
            ),
            reflectionQuestions = listOf(
                "Bugün yaptığım ibadet veya hizmetlerde gizli bir takdir edilme arzusu taşıyor muyum?",
                "Nefsimi 15 günde bir bu düsturlarla murakabe ediyor muyum?"
            )
        ),

        // 2. BİRİNCİ DÜSTUR
        SectionItem(
            id = "birinci_dustur",
            title = "Birinci Düstur: Rıza-yı İlâhî",
            subtitle = "Amelinizde rıza-yı İlâhî olmalı",
            category = CategoryType.DUSTURLAR,
            originalText = """BİRİNCİ DÜSTURUNUZ:
Amelinizde rıza-yı İlâhî olmalı. Eğer O razı olsa, bütün dünya küsse ehemmiyeti yok. Eğer O kabul etse, bütün halk reddetse tesiri yok. O razı olduktan ve kabul ettikten sonra, isterse ve hikmeti iktiza ederse, sizler istemek talebinde olmadığınız halde, halklara da kabul ettirir, onları da razı eder. Onun için, bu hizmette doğrudan doğruya yalnız Cenab-ı Hakk'ın rızasını esas maksad yapmak gerektir.

Eğer insanların teveccühü ve takdiri, amel-i salihin bir mükâfatı ve neticesi olarak verilirse, istenilmemek şartıyla ve nefse gurur vermemek kaydıyla bir sevgidir, zararsızdır. Fakat eğer bizzat matlub ve maksad olursa, ameli ifsâd eder, riya olur, ihlası kaçırır.""",
            modernExplanation = """İhlasın en birinci ve sarsılmaz temeli, amellerde yalnızca Allah Teâlâ'nın rızasını gaye edinmektir. İnsanların övgüsü, alkışı, teveccühü veya tenkidi hedef olamaz. 

Eğer Allah yaptığınız işten razı ise, bütün cihan size cephe alsa hiçbir zararı dokunmaz. Tersine, bütün dünya sizi alkışlasa fakat Allah razı değilse hiçbir kıymeti yoktur. İnsanların hürmet ve sevgisi ancak istenmeden, sırf İlahi bir lütuf olarak gelirse kabul edilir; fakat bizzat o sevgi ve alkış için amel işlenirse o amel riyaya döner ve ibadet batıl olur.""",
            keyLessons = listOf(
                "Esas gaye yalnız Cenab-ı Hakk'ın rızasıdır; halkın teveccühü aranmaz.",
                "Allah razı olduktan sonra, dilerse halkı da razı eder; kulun vazifesi rızayı aramaktır.",
                "Vazife-i İlâhiyeye karışılmaz: Tesir halk etmek Allah'a aittir, kul yalnız tebliğ ve amelle mükelleftir.",
                "Övgü ve beğeni arayışı gizli riyadır ve ihlası zedeler."
            ),
            reflectionQuestions = listOf(
                "Bir iş yaparken insanların teşekkür etmemesi veya eleştirmesi beni üzüyor mu?",
                "Kimsenin görmediği yerdeki gayretim ile insanların gördüğü yerdeki gayretim aynı mı?"
            )
        ),

        // 3. İKİNCİ DÜSTUR
        SectionItem(
            id = "ikinci_dustur",
            title = "İkinci Düstur: Kardeşleri Tenkit Etmemek",
            subtitle = "Faziletfuruşluk yapmamak ve gıbta damarını tahrik etmemek",
            category = CategoryType.DUSTURLAR,
            originalText = """İKİNCİ DÜSTURUNUZ:
Bu hizmet-i Kur'âniyede bulunan kardeşlerinizi tenkid etmemek ve onların üstünde faziletfuruşluk nev'inden gıbta damarını tahrik etmemektir. Çünki nasıl insanın bir eli diğer eline rekabet etmez, bir gözü bir gözünü tenkid etmez, dili kulağına itiraz etmez, kalb ruhun ayıbını görmez... Belki birbirinin noksanını ikmal eder, kusurunu örter, ihtiyacına yardım eder, vazifesine muavenet eder. Yoksa o vücud-u insanın hayatı söner, ruhu kaçar, cismi de dağılır.

Hem nasılki bir fabrikanın çarkları birbiriyle rekabetkârane uğraşmaz, birbirinin önüne tekaddüm edip tahakküm etmez, birbirinin kusurunu görüp tenkid ederek sa'ye hevesini kırıp atalete uğratmaz. Belki bütün istidadlarıyla birbirinin hareketini umumî maksada tevcih etmek için yardım ederler, hakikî bir tesanüd bir ittifak ile gaye-i hilkatlerine yürürler... Aynen öyle de; Nur şakirdleri bir tek vücudun âzaları ve bir fabrikanın çarkları hükmündedir.""",
            modernExplanation = """Bediüzzaman burada manevi hizmetlerdeki kardeşler arasındaki münasebeti muazzam benzetmelerle açıklar:
1. İnsan Bedeni Temsili: Sağ el sol ele düşman olur mu? Göz kulağa haset eder mi? Asla. Birbirinin eksiğini tamamlar.
2. Fabrika Temsili: Çarklar birbiriyle yarışmaz, biri ötekinin işini bozmaz; müşterek bir gayeye hizmet ederler.

Hizmet arkadaşlarını tenkit etmek, onlara üstünlük taslamak (faziletfuruşluk) ve aralarındaki kıskançlık/rekabet duygularını ateşlemek ihlası kökünden dinamitler. Kusur örtücü olmak, meziyetleri takdir etmek esastır.""",
            keyLessons = listOf(
                "Dava arkadaşlarını tenkit etmek ve kusurlarını yaymak haram ve ihlassızlıktır.",
                "Faziletfuruşluk (kendini daha bilgili, daha takvalı, daha üstün göstermek) kardeşlik ruhunu öldürür.",
                "Müminler bir bedenin uzuvları gibidir; biri ağrırsa hepsi o acıyı paylaşır.",
                "Fabrikanın çarkları gibi ahenkle çalışmak, rekabeti değil yardımlaşmayı gerektirir."
            ),
            reflectionQuestions = listOf(
                "Kardeşimin veya arkadaşımın bir başarısını duyduğumda içimde kıskançlık mı yoksa sevinç mi uyanıyor?",
                "Başkalarının kusurlarını aramak yerine kendi nefsimin noksanlarına bakıyor muyum?"
            )
        ),

        // 4. ÜÇÜNCÜ DÜSTUR
        SectionItem(
            id = "ucuncu_dustur",
            title = "Üçüncü Düstur: Kuvveti İhlas ve Hakta Bilmek",
            subtitle = "111 Sırrı, Hakiki Tesanüd ve Şahs-ı Manevî",
            category = CategoryType.DUSTURLAR,
            originalText = """ÜÇÜNCÜ DÜSTURUNUZ:
Bütün kuvvetinizi ihlasta ve hakta bilmelisiniz. Evet, kuvvet haktadır ve ihlastadır. Haksızlar dahi, haksızlıkları içinde gösterdikleri ihlas ve samimiyet yüzünden kuvvet kazanıyorlar. 

Evet, sırr-ı ihlas ile cilvelenen hakikî bir tesanüd; üç elifin (1 1 1) ittihadıyla yüz on bir kıymetini aldığı gibi, hakikî üç hâdim-i din yan yana gelse, yüz on bir kuvvetinde bir şahs-ı manevî teşkil eder. Dörde dört elif (1 1 1 1) olsa bin yüz on bir kuvvetini verir. Nasılki on altı adamın ayrı ayrı çalışması on altı adam kuvvetindedir; fakat sırr-ı uhuvvet ve müşterek gaye ile çalışan on altı adam, bin yüz on bir kuvvetinde bir şahs-ı manevî tecelli ettirir.

Çünki hakikî ihlaslı bir hizmette enaniyet kalkar, 'ben' gider 'biz' gelir. Herkes kardeşinin gözüyle bakar, kulağıyla işitir, aklıyla düşünür.""",
            modernExplanation = """Gerçek kuvvet kemiyette (sayı çokluğunda) değil, keyfiyette (samimiyet ve doğrulukta) dir. Hatta haksız davada olanlar dahi kendi aralarında samimi ve ihlaslı bir dayanışma gösterdiklerinde geçici bir güç elde edebilirler. 

Meşhur "111 Sırrı": 
Matematikte üç tane bir (1, 1, 1) alt alta yazılıp toplanırsa sadece "3" eder. Fakat sırr-ı tesanüd ve ihlas ile omuz omuza verip yan yana gelirlerse "111" kıymetini kazanırlar. Dört kişi yan yana gelse "1111" kuvvetine ulaşır. Benliği eriten müminler, fert olmaktan çıkıp yenilmez bir 'Şahs-ı Manevî' kuvvetine bürünürler.""",
            keyLessons = listOf(
                "Hakiki kuvvet çoklukta değil, ihlas ve doğrulukta saklıdır.",
                "Tesanüd sırrı: Birlikte hareket eden az sayıdaki samimi insan, dağınık binlerden daha güçlüdür.",
                "111 Sırrı: Bir araya gelen fertler kabiliyetlerini çarpana dönüştürürler.",
                "'Ben' değil 'Biz' diyebilmek şahs-ı manevî havuzuna dahil olmanın şartıdır."
            ),
            reflectionQuestions = listOf(
                "Kendi fikir ve projelerimi cemaatin ve kardeşlerimin ortak aklının üstünde mi görüyorum?",
                "Yalnız başıma hareket etmek yerine şahs-ı manevinin gücüne güveniyor muyum?"
            )
        ),

        // 5. DÖRDÜNCÜ DÜSTUR
        SectionItem(
            id = "dorduncu_dustur",
            title = "Dördüncü Düstur: Fena fi'l-İhvan ve Tefani",
            subtitle = "Kardeşlerinin meziyetleriyle şakirâne iftihar etmek",
            category = CategoryType.DUSTURLAR,
            originalText = """DÖRDÜNCÜ DÜSTURUNUZ:
Kardeşlerinizin meziyetlerini şahıslarınızda ve faziletlerini kendinizde tasavvur edip, onların şerefleriyle şâkirane iftihar etmektir. 

Ehl-i tasavvufun mabeyninde 'fena fiş-şeyh', 'fena fir-resul' tabir edilen bir mertebe-i kemalât vardır. Ben de o meslek-i âlinin bir nev'ini mesleğimizde görüyorum ki; 'fena fi'l-ihvan' namını veriyorum. Yani, kardeşleri içinde fani olmak; yani, kendi enaniyetini onların meziyetleri içinde eritmek, onların meziyetleriyle sevinmek, onların şerefiyle iftihar etmektir.

Bir kimse meselâ bir çeşmeden su içse, o suyun lezzetini kendi ağzında hissettiği gibi; kardeşinin ilmiyle, irfanıyla, takvasıyla, güzel hitabetiyle hasıl olan feyzi kendi hissesi bilip Allah'a şükretmelidir. Bu hal, hasedi kökünden keser, gıybeti men eder, muhabbeti zevalsiz kılar.""",
            modernExplanation = """Tasavvuf terbiyesinde kişinin mürşidinde veya Peygamber Efendimiz'de (a.s.m.) benliğini eritmesi makamları vardır. Bediüzzaman bu asrın Kur'an talebelerine "Fena fi'l-ihvan" (kardeşlerinde fani olmak, tefani) düsturunu hediye eder.

Bu düstur şudur: Kardeşin bir hayır yaptıysa, bir hakikati güzelce anlattıysa, sanki sen yapmışsın gibi sevinmek ve Allah'a şükretmektir. "Ben değil o yaptı, keşke ben yapsaydım" demek gizli enaniyettir. Kardeşinin meziyetini kendi meziyeti sayan kalpte haset, kıskançlık, arkadan konuşma ve kin asla barınamaz.""",
            keyLessons = listOf(
                "Fena fi'l-ihvan: Kardeşlerin şeref ve faziletiyle kendi fazileti gibi şükrederek iftihar etmektir.",
                "Tefani sırrı nefsin en gizli hastalığı olan haset ve çekememezliğin kesin ilacıdır.",
                "Hayırlı bir işi kimin yaptığı mühim değildir; mühim olan o hayrın yapılması ve Allah'ın rızasıdır.",
                "Kardeşinin muvaffakiyetine kendi muvaffakiyeti gibi sevinen insan manen o sevaba bizzat ortaktır."
            ),
            reflectionQuestions = listOf(
                "Benden daha kabiliyetli veya daha çok övülen bir arkadaşım olduğunda kalbimde ne hissediyorum?",
                "Onun başarısını kendi başarımmış gibi alkışlayıp şükredebiliyor muyum?"
            )
        ),

        // 6. İHLASI KIRAN MANİLER
        SectionItem(
            id = "ihlas_manileri",
            title = "İhlası Kıran Mâniler ve İlaçları",
            subtitle = "Menfaat hırsı, Hubb-u Câh ve Korku Desiseleri",
            category = CategoryType.MANILER,
            originalText = """İHLASI KIRAN VE RİYAYA SEVK EDEN SEBEPLER:

1. BİRİNCİ MÂNİ: Menfaat-i maddiye cihetinden gelen rekabet ve hırs.
Ehl-i dünya nasıl menfaat-i maddiye için yarışırlar; bazen ehl-i din dahi o hırsa kapılıp birbirine rekabet eder. Halbuki rızık taahhüd-ü Rabbanî altındadır. Rızk-ı helal kanaattedir. Kanaat eden, hırsın zilletinden ve rekabetin zehrinden kurtulur.

2. İKİNCİ MÂNİ: Hubb-u câh (makam, mansıb, şöhret sevgisi ve teveccüh-ü nâs).
İnsanın fıtratında bulunan kendini beğendirme, takdir edilme ve yüksek görünme arzusu; ihlası kıran en mühim bir marazdır. Halkın takdirini aramak, insanı riyaya ve müraîliğe sürükler. İlacı: Teveccüh-ü nâsın fani, kabir kapısında hükümsüz ve ameli ibtal edici olduğunu bilmektir.

3. ÜÇÜNCÜ MÂNİ: Korku ve tama'.
Ehl-i dalaletin ve düşmanların tazyikinden, mevkiini veya dünyalığını kaybetmekten korkmak. İlacı: Ecel birdir, taayyün etmiştir, tebeddül etmez. Cenab-ı Hakk'ın hıfzına tevekkül edip, O'ndan gayrısından korkmamaktır.""",
            modernExplanation = """İhlası zedeleyen üç büyük düşman vardır:
1. Maddi Menfaat & Hırs: Dünyalık kazanma, gelir, makam veya bağış toplama rekabeti. Çaresi: Rızkın Allah'ın teminatı altında olduğuna inanmak ve kanaat kalesine sığınmaktır.
2. Hubb-u Câh (Makam ve Şöhret Sevgisi): İnsanların beğenisini aramak, alkışlanmak, 'büyük adam' bilinmek arzusu. Bu his amelin içine sızarsa samimiyet biter, riya başlar.
3. Korku ve Zaafiyet: İnsanlardan, zalimlerden veya fakirlikten korkup haktan taviz vermek. Çaresi: Ecelin bir olduğunu, Allah dilemedikçe kimsenin zarar veremeyeceğini idrak etmektir.""",
            keyLessons = listOf(
                "Maddi menfaat peşinde koşmak manevi hizmetlerin bereketini kaçırır.",
                "Hubb-u câh (şöhret tutkusu) zehirli bir baldır; anlık lezzet verir, ebedi ameli mahveder.",
                "Kanaat tükenmez bir hazinedir; hırs ise zillet ve mahrumiyet sebebidir.",
                "Korku damarını ehl-i dalalet istismar eder; hakiki tevekkül korkuyu izale eder."
            ),
            reflectionQuestions = listOf(
                "İnsanların beni beğenmesi ve hakkımda iyi konuşması benim için ne kadar önemli?",
                "Maddi kaygılar yüzünden manevi vazifelerimde gevşeklik gösteriyor muyum?"
            )
        ),

        // 7. İHLASI KAZANDIRAN İKİ ÇARE
        SectionItem(
            id = "ihlas_careleri",
            title = "İhlası Kazandıran İki Müessir Sebep",
            subtitle = "Râbıta-i Mevt ve Marifetullah ile Huzur-u Daimî",
            category = CategoryType.CARE_LER,
            originalText = """İHLASI KAZANDIRAN VE MUHAFAZA EDEN İKİ MÜESSİR SEBEP:

BİRİNCİ SEBEP: RÂBITA-I MEVT'TİR.
Yani ölümü düşünmektir. Evet, ihlası zedeleyen tûl-i emel olduğu gibi; riyadan nefret veren ve ihlası kazandıran râbıta-i mevttir. Ehl-i tarikat sülûklarında râbıta-i mevti esas tutmuşlar. Fakat hakikat mesleğinde olanlar, hayalî faraziyelerle değil; doğrudan doğruya akıbetine, kendi cenazesine, asrının ölümüne ve dünyanın fena bulacağına hakikat gözüyle bakarlar. Ölümü düşünen nefis, fani dünyanın sahte iltifatlarına aldanmaz, riyaya tenezzül etmez.

İKİNCİ SEBEP: İMAN-I TAHKİKÎNİN KUVVETİYLE VE MARİFET-İ SÂNİ'İN NURLARIYLA HUZUR-U DAİMÎ KAZANMAKTIR.
Her şeyde Cenab-ı Hakk'ın tecelliyatını müşahede etmek; O'nun her an bizi gördüğünü, bildiğini idrak etmektir. Bir insan padişahın huzurunda olduğunu bilse, başkalarına yaranmak için maskaralık yapmaz. Aynen öyle de; her an Hâlık-ı Zülcelal'in huzurunda olduğunu hisseden bir kul, mahlukatın kuru teveccühü için amellerini feda etmez.""",
            modernExplanation = """Bediüzzaman Hazretleri ihlası daimi kılmak için iki eşsiz manevi reçete sunar:

1. Râbıta-i Mevt (Ölüm Hakikatini Hatırlamak):
İnsanı riyaya, hırsa ve gösterişe iten temel yanılgı "tûl-i emel"dir (dünyada hiç ölmeyecekmiş gibi uzun vadeli heveslere kapılmak). Çözüm ise öleceğini tefekkür etmektir. Bir gün musalla taşına konacağını, bedeninin toprağa gireceğini, ardından asrının ve nihayet dünyanın kıyametle vefat edeceğini idrak eden akıl, artık üç kuruşluk fani iltifatlara tenezzül edemez.

2. Marifetullah ve Huzur-u Daimî:
Tahkiki iman ile her varlıkta Allah'ın sanatını ve kudretini görmek. Her an Cenab-ı Hakk'ın huzurunda, O'nun nezaretinde yaşadığının şuuruna varmak. Padişahın huzurundaki bir memur etraftaki hizmetçilere kendini beğendirmekle uğraşmaz; hakiki huzur şuuru da riyayı tamamen silip süpürür.""",
            keyLessons = listOf(
                "Râbıta-i mevt tûl-i emeli kırar, nefsi riyadan ve gösterişten arındırır.",
                "Risale-i Nur'da râbıta-i mevt hayalî değil; hakikatin ta kendisi olan kendi cenazemize bakmaktır.",
                "Huzur-u daimî; her an Allah'ın bizi gördüğü şuuruyla yaşamaktır (ihsan makamı).",
                "Allah'ın huzurunda olduğunu bilen bir kul, mahlûkatın alkışına muhtaç olmaz."
            ),
            reflectionQuestions = listOf(
                "Kabir kapısını ve öleceğimi gün içinde ne sıklıkla aklıma getiriyorum?",
                "Amellerimde 'Allah beni görüyor' hissiyatını ne derece yakalayabiliyorum?"
            )
        ),

        // 8. 20. LEM'A ÖZET VE TAHLİL
        SectionItem(
            id = "yirminci_lema",
            title = "20. Lem'a: İhtilaf Sebepleri ve Reçeteler",
            subtitle = "Ehl-i Hakkın İttifaksızlığı ve Uhuvvet Esasları (9 Sebep)",
            category = CategoryType.YIRMINCI_LEMA,
            originalText = """YİRMİNCİ LEM'A - İHLAS HAKKINDA (ÖZET VE ESASLAR):
Mühim bir sual: Neden ehl-i dünya ve ehl-i dalalet ittifak ettikleri halde, ehl-i hak ve ehl-i hidayet ihtilaf ediyorlar?

Bediüzzaman bu dehşetli yaraya 9 Sebep ve 9 Reçete ile cevap verir:

1. Ehl-i dalaletin ittifakı menfaat müşterekliğindendir; ehl-i hak ise ahiret sevabı peşindedir, fakat nefis araya girip uhrevi makamda dahi rekabet ettirir.
2. Ehl-i hak mesleğini 'hak' bilmeli, fakat 'hak yalnız benim mesleğimdir' dememelidir. 'Benim mesleğim haktır veya daha güzeldir' diyebilir; başkasının mesleğini batıl ilan edemez.
3. Hakiki ehl-i hak, enaniyetini şahs-ı manevî havuzuna atıp eritir. 'Ene' (ben) gider, 'Nahnu' (biz) gelir.
4. Vazife-i İlâhiyeye karışmamak: Kabul ettirmek ve muvaffak kılmak Allah'ındır. Biz yalnız tebliğ ve sadakatle mükellefiz.
5. Ortak büyük düşman karşısında cüz'î ihtilafları bir kenara bırakmak ehl-i aklın ve imanın şiarıdır.""",
            modernExplanation = """20. Lem'a, 21. Lem'a'nın kardeşi ve temel zeminidir. Müslümanların ve ehl-i hakikatin neden birbiriyle ihtilaf ettiğini ve bu ihtilafın nasıl çözüleceğini cerrahi bir hassasiyetle açıklar:

- En Tehlikeli Yanılgı: "Hak yalnız benim yolumdur" taassubudur. Doğrusu: "Benim yolum haktır veya daha güzeldir; fakat başkalarının meşru meslekleri de haktır" diyebilmektir.
- Havuz Temsili: Her fert kendi küçük buz parçası hükmündeki enaniyetini o kutsi şahs-ı manevi havuzuna atıp eritmelidir ki tatlı ve umumi bir kevser havuzu meydana gelsin.
- Vazife şuuru: Neticeye karışmamak, muvaffakiyet beklentisine girmeden yalnız emredilen vazifeyi ihlasla yapmaktır.""",
            keyLessons = listOf(
                "'Hak yalnız benim mesleğimdir' taassubu ehl-i hakkı bölen en büyük afettir.",
                "Ene buzunu şahs-ı manevi havuzunda eritmek şarttır.",
                "Müşterek düşman ve tehlikeler karşısında cüz'î meşrep ihtilafları dondurulmalıdır.",
                "Muvaffakiyet Allah'ın lütfudur, kulun tek vazifesi ihlas ve istikamettir."
            ),
            reflectionQuestions = listOf(
                "Farklı meşrep veya cemaatlerdeki kardeşlerime karşı kalbimde bir soğukluk hissediyor muyum?",
                "Kendi fikirlerimi mutlak hakikat gibi görüp başkalarını dışlıyor muyum?"
            )
        )
    )

    val glossary: List<GlossaryItem> get() = GlossaryHelper.allGlossaryItems
}


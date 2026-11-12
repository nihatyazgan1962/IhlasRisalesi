package com.ihlasrisalesi.analiz.data

import java.util.Locale

object GlossaryHelper {

    // İhlas Risalesi (21. Lem'a) ve 20. Lem'a'daki tüm kavramların geniş lügatçesi
    val allGlossaryItems: List<GlossaryItem> = listOf(
        GlossaryItem(
            term = "İhlâs",
            meaning = "Amelleri, ibadetleri ve hizmetleri sırf Allah rızası için yapmak; dünyevî, maddî veya nefsanî hiçbir menfaat ve karşılık beklememek, samimiyetin zirvesi.",
            contextInIhlas = "İhlas Risalesi'nin ana ekseni; uhrevî hizmetlerin ruhu, en metin nokta-i istinadı ve en makbul şefaatçisidir."
        ),
        GlossaryItem(
            term = "Rıza-yı İlâhî",
            meaning = "Allah Teâlâ'nın razı ve hoşnut olması; bir müminin amellerindeki en birinci, en nihai ve en yüce maksadı.",
            contextInIhlas = "Birinci Düstur'un esası: 'Amelinizde rıza-yı İlâhî olmalı. Eğer O razı olsa, bütün dünya küsse ehemmiyeti yok.'"
        ),
        GlossaryItem(
            term = "Nokta-i İstinad",
            meaning = "Dayanak noktası; aciz ve muhtaç olan insanın güvenip arkasını dayadığı sarsılmaz manevi sığınak.",
            contextInIhlas = "Mukaddime'de: Uhrevi hizmetlerde ihlas, yıkılmaz ve tükenmez 'en metin bir nokta-i istinad' olarak tarif edilir."
        ),
        GlossaryItem(
            term = "Tarîk-i Hakikat",
            meaning = "Hakikat yolu; insanı vehimlerden, riyadan ve batıl heveslerden kurtarıp doğrudan doğruya gerçeğe ulaştıran cadde.",
            contextInIhlas = "İhlas; insanı neticeye en selametli ve en süratli ulaştıran 'en kısa bir tarîk-i hakikat'tir."
        ),
        GlossaryItem(
            term = "Dua-yı Mânevî",
            meaning = "Sözlerle değil, ihlaslı amellerle, lisan-ı hal ile ve samimi niyetle yapılan, tesiri ve kabülü kesin manevi dua.",
            contextInIhlas = "İhlas ile yapılan ameller dergâh-ı İlâhîde 'en makbul bir dua-yı mânevî' hükmüne geçer."
        ),
        GlossaryItem(
            term = "Vesile-i Makasıd",
            meaning = "Maksatlara, hayırlı hedeflere ve meşru emellere ulaştıran bereketli vasıta.",
            contextInIhlas = "İhlas; meşru gayelere ulaştıran 'en kerametli bir vesile-i makasıd'dır."
        ),
        GlossaryItem(
            term = "Haslet",
            meaning = "İnsanın fıtratında bulunan veya güzel ahlakla kazanılan üstün huy, karakter ve seciye.",
            contextInIhlas = "İhlas; insan ruhunu güzelleştiren ve meleklerin derecesine yükselten 'en âli bir haslet'tir."
        ),
        GlossaryItem(
            term = "Ubudiyet",
            meaning = "Kulluk; Allah'a karşı kendi acz, fakr ve kusurlarını idrak ederek boyun eğmek ve ibadet etmek.",
            contextInIhlas = "İhlas; gösterişten ve şirk-i hafîden arınmış 'en safi bir ubudiyet'tir."
        ),
        GlossaryItem(
            term = "Lem'a",
            meaning = "Parıltı, ışık şulesi; Risale-i Nur Külliyatı'ndan Lem'alar mecmuasının her bir risalesi.",
            contextInIhlas = "İhlas Risalesi, Lem'alar kitabının 21. Lem'asıdır; her 15 günde bir okunması tavsiye edilmiştir."
        ),
        GlossaryItem(
            term = "Lâakal",
            meaning = "En azından, asgarî olarak, hiç olmazsa.",
            contextInIhlas = "Mukaddime'de: 'Bu Lem'a lâakal her on beş günde bir defa okunmalı' ihtarında geçer."
        ),
        GlossaryItem(
            term = "Uhrevî",
            meaning = "Ahirete ait, ebedî hayatla ve Allah rızasıyla ilgili olan işler.",
            contextInIhlas = "Uhrevi hizmetlerde ihlas olmazsa amel ruhtan mahrum kalır ve ziyan olur."
        ),
        GlossaryItem(
            term = "Envâr",
            meaning = "Nurlar, aydınlıklar; manevi feyiz ve hakikat ışıkları.",
            contextInIhlas = "'Madem ihlasta mezkûr hassalar gibi çok envâr bulunur...' cümlesinde geçer."
        ),
        GlossaryItem(
            term = "Mezkûr",
            meaning = "Yukarıda zikredilen, anılan, sözü edilen.",
            contextInIhlas = "İhlas Risalesi'nin girişindeki dokuz harika haslet kastedilmektedir."
        ),
        GlossaryItem(
            term = "Tazyikat",
            meaning = "Baskılar, zorlamalar, manevi ve maddi sıkıştırmalar.",
            contextInIhlas = "Zamanın şiddetli tazyikat ve hücumları karşısında müminin tek zırhı ihlastır."
        ),
        GlossaryItem(
            term = "Savletli",
            meaning = "Şiddetle saldıran, heybetli ve tahripkâr hücum eden.",
            contextInIhlas = "Savletli bid'alar ve dalaletler karşısında az ve zayıf olan Nur talebeleri ihlasa sarılmalıdır."
        ),
        GlossaryItem(
            term = "Bid'a",
            meaning = "Dinde aslı olmayan, sonradan uydurulan ve sünnet-i seniyyeyi zedeleyen zararlı yenilikler.",
            contextInIhlas = "Ahir zamanın savletli bid'alarına karşı sünnete ve ihlasa yapışmak farzdır."
        ),
        GlossaryItem(
            term = "Dalâlet",
            meaning = "Doğru yoldan, hidayetten sapma; inkâr, batıl inanç ve sapkınlık.",
            contextInIhlas = "Dalalet hücumunda ancak ihlasın manevi sırrıyla istikamet muhafaza edilebilir."
        ),
        GlossaryItem(
            term = "Hizmet-i Kudsiyye",
            meaning = "Kur'an ve iman hakikatlerini insanlara ulaştırma yolundaki mukaddes vazife.",
            contextInIhlas = "İhlas kaybedilirse, şimdiye kadar kazanılan kutsal hizmetler dahi zayi olabilir."
        ),
        GlossaryItem(
            term = "Teveccüh-ü Âmme",
            meaning = "Halkın bir kimseye yönelmesi, hürmet etmesi, onu övmesi ve takdir etmesi.",
            contextInIhlas = "Teveccüh-ü âmme istenilmeden gelirse bir ikramdır; fakat gaye yapılırsa riya olur ve ihlası kaçırır."
        ),
        GlossaryItem(
            term = "Amel-i Salih",
            meaning = "Allah'ın rızasına ve Kur'an'ın emirlerine uygun, samimi olarak işlenen güzel ve hayırlı amel.",
            contextInIhlas = "Birinci Düstur'da amel-i salihin tek maksadının rıza-yı İlâhî olması gerektiği bildirilir."
        ),
        GlossaryItem(
            term = "İfsâd",
            meaning = "Bozmak, fesada vermek, tahrip etmek.",
            contextInIhlas = "İnsanların takdirini ve şöhreti maksat yapmak ameli ifsâd eder."
        ),
        GlossaryItem(
            term = "Riyâ",
            meaning = "Gösteriş; ibadet ve hayırlarda Allah'tan başkasının beğenisini aramak, ikiyüzlülük (şirk-i hafî).",
            contextInIhlas = "İhlasın zıddı ve baş düşmanıdır; amellerin manevi sevabını yakıp kül eder."
        ),
        GlossaryItem(
            term = "Vazife-i İlâhiye",
            meaning = "Kalpleri imana ısındırmak, hidayet yaratmak ve kabul ettirmek gibi yalnız Cenab-ı Hakk'a ait işler.",
            contextInIhlas = "Kulun vazifesi tebliğdir; kabul ettirmek Allah'ın vazifesidir. Kul Vazife-i İlâhiyeye karışmamalıdır."
        ),
        GlossaryItem(
            term = "Faziletfuruşluk",
            meaning = "Kendi fazilet ve meziyetlerini başkalarına satmak, manevi üstünlük taslamak, böbürlenmek.",
            contextInIhlas = "İkinci Düstur'da kardeşler arasında gıbta ve rekabeti tahrik ettiği için şiddetle yasaklanmıştır."
        ),
        GlossaryItem(
            term = "Gıbta",
            meaning = "Kıskanmadan hayranlık duyma; 'ne güzel, keşke bende de olsa' hissi.",
            contextInIhlas = "Kardeşler arasında gıbta damarını tahrik etmek dahi ihlası zedeleyebilir."
        ),
        GlossaryItem(
            term = "Muavenet",
            meaning = "Karşılıklı yardımlaşma, birbirinin eksikliğini tamamlama ve destek olma.",
            contextInIhlas = "Vücudun uzuvları gibi kardeşler birbirinin vazifesine muavenet etmelidir."
        ),
        GlossaryItem(
            term = "Tekaddüm",
            meaning = "Öne geçmek, kendini diğer kardeşlerinin önüne geçirmeye çalışmak.",
            contextInIhlas = "Fabrikanın çarkları gibi; çarklar birbirinin önüne tekaddüm edip tahakküm etmez."
        ),
        GlossaryItem(
            term = "Tahakküm",
            meaning = "Baskı kurmak, zorbalıkla hükmetmeye ve otorite sağlamaya kalkışmak.",
            contextInIhlas = "Hizmet kardeşleri birbirine tahakküm edemez; eşit ve mütevazı kardeşlerdir."
        ),
        GlossaryItem(
            term = "Atalet",
            meaning = "Tembellik, hareketsizlik, şevkin kırılmasıyla iş yapamaz hale gelmek.",
            contextInIhlas = "Kardeşini tenkit eden, onun sa'ye hevesini kırıp onu atalete uğratır."
        ),
        GlossaryItem(
            term = "Tesanüd",
            meaning = "Birbirine dayanmak, omuz omuza verip kenetlenmek, manevi dayanışma.",
            contextInIhlas = "Üçüncü Düstur'da hakiki tesanüdün 3 adamı 111 kuvvetine ulaştırdığı sırr-ı ihlasla açıklanır."
        ),
        GlossaryItem(
            term = "İttihad",
            meaning = "Birleşmek, tek yürek olmak, müşterek gaye etrafında kenetlenmek.",
            contextInIhlas = "Üç elifin (1 1 1) ittihadıyla yüz on bir kıymetini alması temsilinde geçer."
        ),
        GlossaryItem(
            term = "Hâdim-i Din",
            meaning = "Dine, imana ve Kur'an'a samimiyetle hizmet eden ve hayatını vakfeden hadim.",
            contextInIhlas = "Hakiki üç hâdim-i din bir araya gelse, yüz on bir kuvvetinde bir şahs-ı manevî teşkil eder."
        ),
        GlossaryItem(
            term = "Şahs-ı Manevî",
            meaning = "Aynı kutsi gaye etrafında ihlasla birleşen fertlerin oluşturduğu kolektif tüzel şahsiyet ve ruh.",
            contextInIhlas = "Fertlerin tek başına yapamadığını, ihlasla kenetlenen şahs-ı manevi binler kuvvetiyle başarır."
        ),
        GlossaryItem(
            term = "Enaniyet",
            meaning = "Benlik, ego, kendini beğenme ve merkezde görme hastalığı.",
            contextInIhlas = "Hakiki ihlaslı hizmette enaniyet kalkar; 'ben' gider 'biz' gelir."
        ),
        GlossaryItem(
            term = "Fena fi'l-İhvan",
            meaning = "Kardeşlerinde fani olmak; kendi benliğini onların meziyetleri içinde eritip onların başarısıyla iftihar etmek.",
            contextInIhlas = "Dördüncü Düstur'un ana rüknüdür; hasedi, gıybeti ve kıskançlığı kökünden kurutan iksirdir."
        ),
        GlossaryItem(
            term = "Tefani",
            meaning = "Kardeşleri içinde benliği unutmak ve kendi nefsini kardeşlerinin fazileti içinde fani kılmak.",
            contextInIhlas = "Fena fi'l-ihvan mesleğinin esasıdır: 'Kendi şerefiyle değil, kardeşinin şerefiyle iftihar etmektir.'"
        ),
        GlossaryItem(
            term = "Fena fiş-Şeyh",
            meaning = "Tasavvuf terbiyesinde müridin kendi iradesini mürşidinin iradesinde fani kılması mertebesi.",
            contextInIhlas = "Bediüzzaman bu mertebenin Nur mesleğindeki karşılığının 'fena fi'l-ihvan' olduğunu belirtir."
        ),
        GlossaryItem(
            term = "Fena fir-Resul",
            meaning = "Peygamber Efendimiz'in (a.s.m.) sünnet-i seniyyesinde benliğini eritip O'nun ahlakıyla ahlaklanmak.",
            contextInIhlas = "Tasavvuftaki yüksek bir kemalat mertebesidir."
        ),
        GlossaryItem(
            term = "Feyz",
            meaning = "İlahi bereket, ilim lezzeti, gönüllere akan manevi aydınlık ve inşirah.",
            contextInIhlas = "Kardeşinin güzel hitabet veya ilmiyle hasıl olan feyzi kendi hissesi bilip sevinmektir."
        ),
        GlossaryItem(
            term = "Taahhüd-ü Rabbanî",
            meaning = "Allah Teâlâ'nın her canlının rızkını bizzat vermeyi taahhüt etmesi ve garanti kılması.",
            contextInIhlas = "İhlası kıran birinci mani olan hırsa karşı 'Rızık taahhüd-ü Rabbanî altındadır' hakikati bildirilir."
        ),
        GlossaryItem(
            term = "Hubb-u Câh",
            meaning = "Makam, mevki, rütbe, şan, şöhret sevgisi ve insanların alkışına müptela olma hastalığı.",
            contextInIhlas = "İhlası kıran ikinci büyük manidir; kabir kapısında sönen aldatıcı bir riyadır."
        ),
        GlossaryItem(
            term = "Tama'",
            meaning = "Açgözlülük, doyumsuzluk, harislik; insanlardan menfaat umarak zillet altına girmek.",
            contextInIhlas = "İhlası kıran üçüncü manidir; hakiki tevekkül ve kanaat tama' zilletini izale eder."
        ),
        GlossaryItem(
            term = "Râbıta-i Mevt",
            meaning = "Ölümü, kabri ve dünyanın faniliğini tefekkür ederek nefsin tûl-i emelini kırmak.",
            contextInIhlas = "İhlası kazandıran ve muhafaza eden en tesirli birinci çaredir."
        ),
        GlossaryItem(
            term = "Tûl-i Emel",
            meaning = "Dünyada hiç ölmeyecekmiş gibi uzun vadeli emeller peşinde koşmak, ebedî dünyada kalacak gibi yaşamak.",
            contextInIhlas = "Riyaya ve dünyaperestliğe sevk eden en büyük illet; râbıta-i mevt bu tûl-i emeli kırar."
        ),
        GlossaryItem(
            term = "İman-ı Tahkikî",
            meaning = "Taklitten kurtulup delillere, tefekküre ve marifetullaha dayanan sarsılmaz kuvvetli iman.",
            contextInIhlas = "İhlası kazandıran ikinci sebep; huzur-u daimîyi ancak tahkiki iman temin eder."
        ),
        GlossaryItem(
            term = "Marifetullah",
            meaning = "Allah Teâlâ'yı isimleri, sıfatları ve kainattaki sanat eserleriyle derinden tanıma ilmi.",
            contextInIhlas = "Marifetullah arttıkça kul daima Allah'ın huzurunda olduğu şuuruna erer ve riyadan kurtulur."
        ),
        GlossaryItem(
            term = "Huzur-u Daimî",
            meaning = "Her an Cenab-ı Hakk'ın huzurunda ve O'nun nezaretinde olduğunu bilmek şuuru (ihsan mertebesi).",
            contextInIhlas = "Padişahın huzurundaki adam hizmetçilere gösteriş yapmadığı gibi, huzur-u daimî de riyayı siler."
        ),
        GlossaryItem(
            term = "Marifet-i Sâni'",
            meaning = "Kainattaki her eserde Yüce Yaratıcı ve Sanatkar olan Allah'ın mührünü ve tecellisini görmek.",
            contextInIhlas = "İhlasın ikinci çaresinde huzur-u daimîyi kazandıran nurdur."
        ),
        GlossaryItem(
            term = "Hâlık-ı Zülcelal",
            meaning = "Sonsuz celal, azamet, büyüklük ve kudret sahibi olan her şeyin Yaratıcısı.",
            contextInIhlas = "Mahlukatın kuru takdiri için Hâlık-ı Zülcelal'in rızası terk edilemez."
        ),
        GlossaryItem(
            term = "Gaye-i Hilkat",
            meaning = "İnsanın ve kainatın yaratılışındaki nihai maksat; Allah'ı tanımak ve O'na ihlasla ibadet etmek.",
            contextInIhlas = "Fabrikanın çarkları gibi müttefik kardeşler hakiki bir tesanüdle gaye-i hilkatlerine yürürler."
        ),
        GlossaryItem(
            term = "111 Sırrı",
            meaning = "Üç tane 1 rakamı alt alta toplanırsa 3 eder; fakat omuz omuza verip yan yana gelirse 111 kıymetini alır.",
            contextInIhlas = "İhlaslı cemaat ruhunun ve hakiki tesanüdün gücü bu matematiksel sırla izah edilir."
        )
    )

    // Arama ve eşleştirme için terimlerin temizlenmiş hallerini indeksle
    private val normalizedTermsMap: Map<String, GlossaryItem> by lazy {
        val map = mutableMapOf<String, GlossaryItem>()
        allGlossaryItems.forEach { item ->
            val n = normalize(item.term)
            map[n] = item
            // Eğer çok kelimeli ise parçalarını da ekle
            val words = item.term.split(" ", "-")
            if (words.size > 1) {
                words.forEach { w ->
                    val nw = normalize(w)
                    if (nw.length >= 4 && !map.containsKey(nw)) {
                        map[nw] = item
                    }
                }
            }
        }
        map
    }

    private val suffixes = listOf(
        "leriniz", "larınız", "lerimiz", "larımız", "lerinden", "larından",
        "lerinin", "larının", "lerinizi", "larınızı", "tiriniz", "tırınız",
        "siniz", "sınız", "siniz", "sunuz", "sünüz", "tür", "tur", "tir",
        "tır", "dür", "dur", "dir", "dır", "ler", "lar", "den", "dan",
        "ten", "tan", "nin", "nın", "nun", "nün", "in", "ın", "un", "ün",
        "de", "da", "te", "ta", "ye", "ya", "yi", "yı", "yu", "yü",
        "si", "sı", "su", "sü", "i", "ı", "u", "ü", "e", "a"
    )

    fun normalize(raw: String): String {
        var s = raw.lowercase(Locale("tr", "TR"))
        val replacements = mapOf(
            'â' to 'a', 'î' to 'i', 'û' to 'u', 'ô' to 'o',
            'ı' to 'i', 'ç' to 'c', 'ğ' to 'g', 'ö' to 'o',
            'ş' to 's', 'ü' to 'u'
        )
        val sb = StringBuilder()
        for (ch in s) {
            val r = replacements[ch]
            if (r != null) {
                sb.append(r)
            } else if (ch.isLetter()) {
                sb.append(ch)
            }
        }
        return sb.toString()
    }

    private fun stripSuffix(normWord: String): String {
        for (suffix in suffixes) {
            if (normWord.endsWith(suffix) && (normWord.length - suffix.length) >= 3) {
                return normWord.substring(0, normWord.length - suffix.length)
            }
        }
        return normWord
    }

    /**
     * Verilen kelimenin lügatçede bir karşılığı olup olmadığını hızlıca kontrol eder.
     */
    fun isGlossaryWord(rawWord: String): Boolean {
        val n = normalize(rawWord)
        if (n.length < 3) return false
        if (normalizedTermsMap.containsKey(n)) return true
        val stem = stripSuffix(n)
        return normalizedTermsMap.containsKey(stem)
    }

    /**
     * Tıklanan kelimeye göre lügatçedeki en uygun terimi bulur.
     * Çevresindeki kelimeleri de dikkate alarak "Rıza-yı İlâhî", "Nokta-i İstinad" gibi terkipleri çözer.
     */
    fun findGlossaryItem(clickedWord: String, allWords: List<String> = emptyList(), clickedIndex: Int = -1): GlossaryItem? {
        // 1. Çok kelimeli tamlama kontrolü (2'li ve 3'lü pencereler)
        if (allWords.isNotEmpty() && clickedIndex in allWords.indices) {
            for (windowSize in listOf(3, 2)) {
                val start = (clickedIndex - windowSize + 1).coerceAtLeast(0)
                val end = (clickedIndex + windowSize).coerceAtMost(allWords.size)
                for (s in start..clickedIndex) {
                    val e = (s + windowSize).coerceAtMost(allWords.size)
                    if (clickedIndex in s until e) {
                        val phrase = allWords.subList(s, e).joinToString(" ")
                        val normPhrase = normalize(phrase)
                        val match = allGlossaryItems.firstOrNull { item ->
                            val normTerm = normalize(item.term)
                            normPhrase == normTerm || normPhrase.contains(normTerm) || normTerm.contains(normPhrase)
                        }
                        if (match != null) return match
                    }
                }
            }
        }

        // 2. Doğrudan kelime eşleşmesi
        val n = normalize(clickedWord)
        if (n.isBlank()) return null

        normalizedTermsMap[n]?.let { return it }

        // 3. Ek atarak kök eşleşmesi
        val stem = stripSuffix(n)
        normalizedTermsMap[stem]?.let { return it }

        // 4. Kısmi içeren terim araması (en az 4 harf)
        if (stem.length >= 4) {
            allGlossaryItems.firstOrNull { item ->
                val nt = normalize(item.term)
                nt.contains(stem) || stem.contains(nt)
            }?.let { return it }
        }

        return null
    }
}


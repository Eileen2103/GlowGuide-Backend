package ai;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class GeminiService {

    public String testCall(String prompt) {
        try {
            System.out.println("QA KONTROL: Çevre Değişkeni Üzerinden SDK Ayağa Kaldırılıyor...");

            //  Kılavuzdaki gibi boş constructor ile çağırr
            // SDK, anahtarı işletim sisteminin GOOGLE_API_KEY değişkeninden otomatik okuyacak.
            Client client = new Client();

            GenerateContentResponse response = client.models.generateContent(
                    "gemini-2.5-flash", 
                    prompt, 
                    null
            );

            return response.text();

        } catch (Exception e) {
            System.err.println("Google SDK Hatası: " + e.getMessage());
            e.printStackTrace();
            return "Yapay zeka yanıtı alınamadı: " + e.getMessage();
        }
    }
}
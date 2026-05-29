package ocr;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OcrService {

    public OcrResultDto extractText(MultipartFile file) {

        // Şimdilik sahte OCR

        String rawText =
                "Water, Niacinamide, Salicylic Acid, Fragrance";

        List<String> ingredients =
				Arrays.asList(
                        "Water",
                        "Niacinamide",
                        "Salicylic Acid",
                        "Fragrance"
                );

        OcrResultDto result = new OcrResultDto();

        result.setRawText(rawText);
        result.setIngredients(ingredients);

        return result;
    }
}

package analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dto.AnalysisResultDto;
import models.User;
import service.UserService;

@RestController
@RequestMapping("/api/analyze")
public class ProductAnalysisController {
	
	
	 @Autowired
	    private ProductAnalysisService analysisService;

	    @Autowired
	    private UserService userService;

	    @PostMapping
	    public ResponseEntity<AnalysisResultDto> analyze(
	            @RequestParam("image") MultipartFile image,
	            @RequestParam("userId") Long userId
	    ) {

			User user = userService.getEntityById(userId);

	        AnalysisResultDto result = analysisService.analyze(image, user);

	        return ResponseEntity.ok(result);
	    }

}

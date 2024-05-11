package aiss.VimeoMiner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Videos not available")

public class CommentsNotFoundAvailableException extends Exception {
}

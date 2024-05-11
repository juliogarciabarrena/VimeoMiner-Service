package aiss.VimeoMiner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Channel Not Found")

public class ChannelNotFoundException extends Exception{
}

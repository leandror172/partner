package com.leandror.ze.partner.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "video not found")
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 2252586730596668853L;

}

package com.groupepsih.psihback.domaine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse {
    private boolean etat;
    private String message;
}

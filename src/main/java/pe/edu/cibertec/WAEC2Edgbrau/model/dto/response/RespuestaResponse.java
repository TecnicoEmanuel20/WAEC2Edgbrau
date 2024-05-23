package pe.edu.cibertec.WAEC2Edgbrau.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaResponse {
    private boolean resultado;
    private String mensaje;
}


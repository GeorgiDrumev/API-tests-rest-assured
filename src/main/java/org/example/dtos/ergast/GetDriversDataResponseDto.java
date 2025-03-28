package org.example.dtos.ergast;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)

public class GetDriversDataResponseDto {

    private String givenName;
    private String familyName;
    private String nationality;
    private Integer permanentNumber;
}

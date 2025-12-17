package com.lshdainty.porest.common.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Enum 타입 정보")
public class TypesDto {
    @Schema(description = "Enum 코드", example = "ANNUAL")
    private String code;

    @Schema(description = "Enum 이름 (화면 표시용)", example = "연차")
    private String name;

    @Schema(description = "정렬 순서", example = "1")
    private Long orderSeq;
}

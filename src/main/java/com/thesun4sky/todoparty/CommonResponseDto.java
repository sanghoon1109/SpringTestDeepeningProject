package com.thesun4sky.todoparty;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // JSON으로 데이터를 패싱할 떄 null인 애들은 빼고 받는 어노테이션
public class CommonResponseDto {
    private String msg;
    private Integer statusCode;
}

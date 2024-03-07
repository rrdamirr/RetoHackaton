package com.banana.bananamint.domain;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@XmlRootElement
public class StatusMessage {
    private Integer status;
    private String message;

}
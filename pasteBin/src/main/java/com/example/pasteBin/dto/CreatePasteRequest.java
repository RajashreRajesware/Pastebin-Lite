package com.example.pasteBin.dto;

import lombok.Data;

@Data

public class CreatePasteRequest {



        private String content;


        private Integer expiryMinutes;


        private Integer maxViews;

}

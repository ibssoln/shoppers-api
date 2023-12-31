package com.ibssoln.shoppers.view;

import lombok.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fileName = "";
    private Map<String, String> pairMap = new HashMap();

}

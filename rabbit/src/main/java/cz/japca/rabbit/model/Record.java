package cz.japca.rabbit.model;

import java.time.LocalDate;

import lombok.Data;

/**
 * Created by Jakub krhovják on 3/11/19.
 */
@Data
public class Record {

    private Long id;
    private String name;
    private String description;
    private LocalDate created;

}

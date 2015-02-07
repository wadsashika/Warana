package com.cse.warana.model;

import javax.persistence.*;

/**
 * Created by Anushka on 2015-02-05.
 */
@Entity
@Table(name = "uploaded_compny_doc")
public class CompanyDocTbl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "file_name")
    private String fileName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}

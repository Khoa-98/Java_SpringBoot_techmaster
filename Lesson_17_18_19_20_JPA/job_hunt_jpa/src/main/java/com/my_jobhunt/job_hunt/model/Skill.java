package com.my_jobhunt.job_hunt.model;

public enum Skill {
    Java("Java"),
    CSharp("Csharp"),
    AWS("AWS"),
    SQL("SQL");

    public final String label;

    private Skill(String label) {
        this.label = label;
    }
}

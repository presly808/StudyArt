package ua.artcode.model.common;

public enum UserType {
    ROLE_ADMIN, ROLE_TEACHER, ROLE_STUDENT;

    public String toFormattedString(){
        switch (this.name()) {
            case "ROLE_TEACHER":
                return "Teacher";
            case "ROLE_STUDENT":
                return "Student";
            default:
                return "";
        }
    }


}


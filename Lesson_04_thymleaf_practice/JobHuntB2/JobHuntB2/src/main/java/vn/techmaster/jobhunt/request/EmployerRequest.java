package vn.techmaster.jobhunt.request;

public record EmployerRequest(
        String id,
        String name,
        String logo_path,
        String website,
        String email

) {
    // MultipartFile logo) {
}
package vn.techmaster.jobhunt.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employer {
  private String id;

  private String name;

  private MultipartFile logo_path;
  private String website;
  private String email;
}

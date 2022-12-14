package dtos;

import java.util.List;

public class CharityDTO {
    String name;
    String description;
    String location;
    String profileUrl;
    String coverImageUrl;
    List<String> tags;
    String slug;

    public CharityDTO(String name, String description, String location,String profileUrl,String coverImageUrl, List<String> tags, String slug) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.profileUrl = profileUrl;
        this.coverImageUrl = coverImageUrl;
        this.tags = tags;
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public String getSlug() {
        return slug;
    }
}

package dev.corp.floturner.thenytimes.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {
    @SerializedName("abstract")
    @Expose
    private String description;
    @SerializedName("byline")
    @Expose
    private String byline;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("des_facet")
    @Expose
    private List<String> desFacet;
    @SerializedName("geo_facet")
    @Expose
    private List<String> geoFacet;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("kicker")
    @Expose
    private String kicker;
    @SerializedName("material_type_facet")
    @Expose
    private String materialTypeFacet;
    @SerializedName("multimedia")
    @Expose
    private List<MultiMedia> multiMedias;
    @SerializedName("org_facet")
    @Expose
    private List<String> orgFacet;
    @SerializedName("per_facet")
    @Expose
    private List<String> perFacet;
    @SerializedName("published_date")
    @Expose
    private String publishedDate;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("short_url")
    @Expose
    private String shortUrl;
    @SerializedName("subsection")
    @Expose
    private String subSection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("updated_date")
    @Expose
    private String updatedDate;
    @SerializedName("url")
    @Expose
    private String url;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getMaterialTypeFacet() {
        return materialTypeFacet;
    }

    public void setMaterialTypeFacet(String materialTypeFacet) {
        this.materialTypeFacet = materialTypeFacet;
    }

    public List<MultiMedia> getMultiMedias() {
        return multiMedias;
    }

    public void setMultiMedias(List<MultiMedia> multiMedias) {
        this.multiMedias = multiMedias;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getSubSection() {
        return subSection;
    }

    public void setSubSection(String subSection) {
        this.subSection = subSection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

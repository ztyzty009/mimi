package cn.zty.demo.model;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb1_user.id
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb1_user.account_id
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb1_user.gmt_create
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb1_user.gmt_modified
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb1_user.name
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb1_user.token
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb1_user.avatar_url
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    private String avatarUrl;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb1_user.id
     *
     * @return the value of tb1_user.id
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb1_user.id
     *
     * @param id the value for tb1_user.id
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb1_user.account_id
     *
     * @return the value of tb1_user.account_id
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb1_user.account_id
     *
     * @param accountId the value for tb1_user.account_id
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb1_user.gmt_create
     *
     * @return the value of tb1_user.gmt_create
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb1_user.gmt_create
     *
     * @param gmtCreate the value for tb1_user.gmt_create
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb1_user.gmt_modified
     *
     * @return the value of tb1_user.gmt_modified
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb1_user.gmt_modified
     *
     * @param gmtModified the value for tb1_user.gmt_modified
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb1_user.name
     *
     * @return the value of tb1_user.name
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb1_user.name
     *
     * @param name the value for tb1_user.name
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb1_user.token
     *
     * @return the value of tb1_user.token
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb1_user.token
     *
     * @param token the value for tb1_user.token
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb1_user.avatar_url
     *
     * @return the value of tb1_user.avatar_url
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb1_user.avatar_url
     *
     * @param avatarUrl the value for tb1_user.avatar_url
     *
     * @mbg.generated Sun Dec 29 13:21:23 CST 2019
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
}
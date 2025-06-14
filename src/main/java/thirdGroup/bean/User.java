package thirdGroup.bean;

public class User {
	private int id;
	private String username;
	private String userpwd;
	private String flag;

    public User() {
    }

    public User(int id, String username, String userpwd, String flag) {
        this.id = id;
        this.username = username;
        this.userpwd = userpwd;
        this.flag = flag;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return userpwd
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * 设置
     * @param userpwd
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    /**
     * 获取
     * @return flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置
     * @param flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User{id = " + id + ", username = " + username + ", userpwd = " + userpwd + ", flag = " + flag + "}";
    }
}

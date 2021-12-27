package Entity;

public class TbStructure {
    //appendonly属性
    boolean appendonly = true;
    //压缩类型
    String compresstype = "zlib";
    //压缩级别
    int compresslevel = 5;
    //表的列和类型，用逗号分隔
    String columnInfo;
    //表名称
    String tbName;
    //分布键
    String distributedKey;

    public String getCompresstype() {
        return compresstype;
    }

    public void setCompresstype(String compresstype) {
        this.compresstype = compresstype;
    }

    public boolean isAppendonly() {
        return appendonly;
    }

    public void setAppendonly(boolean appendonly) {
        this.appendonly = appendonly;
    }

    public int getCompresslevel() {
        return compresslevel;
    }

    public void setCompresslevel(int compresslevel) {
        this.compresslevel = compresslevel;
    }

    public String getColumnInfo() {
        return columnInfo;
    }

    public void setColumnInfo(String columnInfo) {
        this.columnInfo = columnInfo;
    }

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getDistributedKey() {
        return distributedKey;
    }

    public void setDistributedKey(String distributedKey) {
        this.distributedKey = distributedKey;
    }
}

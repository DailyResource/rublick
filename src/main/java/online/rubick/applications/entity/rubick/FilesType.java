package online.rubick.applications.entity.rubick;

import java.io.Serializable;

public class FilesType implements Serializable {
    private String fileTypeCode;

    private String fileTypeName;

    private String remark;

    private static final long serialVersionUID = 1L;

    public String getFileTypeCode() {
        return fileTypeCode;
    }

    public void setFileTypeCode(String fileTypeCode) {
        this.fileTypeCode = fileTypeCode == null ? null : fileTypeCode.trim();
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName == null ? null : fileTypeName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FilesType other = (FilesType) that;
        return (this.getFileTypeCode() == null ? other.getFileTypeCode() == null : this.getFileTypeCode().equals(other.getFileTypeCode()))
            && (this.getFileTypeName() == null ? other.getFileTypeName() == null : this.getFileTypeName().equals(other.getFileTypeName()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFileTypeCode() == null) ? 0 : getFileTypeCode().hashCode());
        result = prime * result + ((getFileTypeName() == null) ? 0 : getFileTypeName().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fileTypeCode=").append(fileTypeCode);
        sb.append(", fileTypeName=").append(fileTypeName);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
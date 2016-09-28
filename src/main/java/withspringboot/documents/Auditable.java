package withspringboot.documents;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AuditorAware;

import java.util.Date;

/*
http://maciejwalkowiak.pl/blog/2013/05/24/auditing-entities-in-spring-data-mongodb/

AuditAware --- somehow not working
----------
@CreatedBy and @LastModifiedBy
In order to use them we need to tell Spring who is a current user.
AuditorAware that will obtain current user (probably from session or Spring Security context – depends on your application):
 */
public class Auditable implements AuditorAware<String> {
    // Part of Mongo Auditing -- It will work only if @EnableMongoAuditing is there
    @CreatedDate
    protected Date createdDate;
    @LastModifiedDate
    protected Date modifiedDate;
    @CreatedBy
    protected String createdBy;// not working
    @LastModifiedBy
    protected String modifiedBy;// not working


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public String getCurrentAuditor() {
        return "Tushar Chokshi";
    }
}

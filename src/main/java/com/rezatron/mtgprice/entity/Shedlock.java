package com.rezatron.mtgprice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Table( name = "shedlock" )
public
class Shedlock {
    @Id
    @Size( max = 64 )
    @Column( name = "name",
             nullable = false,
             length = 64 )
    private String id;

    @Column( name = "lock_until" )
    private Instant lockUntil;

    @Column( name = "locked_at" )
    private Instant lockedAt;

    @Size( max = 255 )
    @Column( name = "locked_by" )
    private String lockedBy;

    public
    String getId() {
        return id;
    }

    public
    void setId(String id) {
        this.id = id;
    }

    public
    Instant getLockUntil() {
        return lockUntil;
    }

    public
    void setLockUntil(Instant lockUntil) {
        this.lockUntil = lockUntil;
    }

    public
    Instant getLockedAt() {
        return lockedAt;
    }

    public
    void setLockedAt(Instant lockedAt) {
        this.lockedAt = lockedAt;
    }

    public
    String getLockedBy() {
        return lockedBy;
    }

    public
    void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

}

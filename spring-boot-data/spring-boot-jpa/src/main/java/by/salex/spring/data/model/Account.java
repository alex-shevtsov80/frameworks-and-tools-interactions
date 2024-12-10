package by.salex.spring.data.model;

import java.io.Serializable;
import java.util.Objects;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity(name = "accounts")
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Account implements Serializable {
    private static final long serialVersionUID = -6046625404945139718L;

    @Id
    private Long id;    
    @Column(name = "client_id")
    private Long clientId;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "amount")
    private Double amount;
    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Account [amount=" + amount + ", version=" + version + "]";
    }

}

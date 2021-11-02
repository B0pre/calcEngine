package org.bopre.samples.calcEngine.data.entity

import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "variable")
class StoredVariable(
    @Id
    @GeneratedValue
    @Column(name = "variable_id")
    val id: UUID?,

    @Column(name = "name")
    val name: String,

    @Column(name = "value")
    var condition: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as StoredVariable

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 0

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}
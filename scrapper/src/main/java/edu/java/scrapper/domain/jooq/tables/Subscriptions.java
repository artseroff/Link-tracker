/*
 * This file is generated by jOOQ.
 */
package edu.java.scrapper.domain.jooq.tables;


import edu.java.scrapper.domain.jooq.DefaultSchema;
import edu.java.scrapper.domain.jooq.Keys;
import edu.java.scrapper.domain.jooq.tables.records.SubscriptionsRecord;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Subscriptions extends TableImpl<SubscriptionsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SUBSCRIPTIONS</code>
     */
    public static final Subscriptions SUBSCRIPTIONS = new Subscriptions();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<SubscriptionsRecord> getRecordType() {
        return SubscriptionsRecord.class;
    }

    /**
     * The column <code>SUBSCRIPTIONS.CHAT_ID</code>.
     */
    public final TableField<SubscriptionsRecord, Integer> CHAT_ID = createField(DSL.name("CHAT_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>SUBSCRIPTIONS.LINK_ID</code>.
     */
    public final TableField<SubscriptionsRecord, Integer> LINK_ID = createField(DSL.name("LINK_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    private Subscriptions(Name alias, Table<SubscriptionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Subscriptions(Name alias, Table<SubscriptionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>SUBSCRIPTIONS</code> table reference
     */
    public Subscriptions(String alias) {
        this(DSL.name(alias), SUBSCRIPTIONS);
    }

    /**
     * Create an aliased <code>SUBSCRIPTIONS</code> table reference
     */
    public Subscriptions(Name alias) {
        this(alias, SUBSCRIPTIONS);
    }

    /**
     * Create a <code>SUBSCRIPTIONS</code> table reference
     */
    public Subscriptions() {
        this(DSL.name("SUBSCRIPTIONS"), null);
    }

    public <O extends Record> Subscriptions(Table<O> child, ForeignKey<O, SubscriptionsRecord> key) {
        super(child, key, SUBSCRIPTIONS);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public UniqueKey<SubscriptionsRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_30;
    }

    @Override
    @NotNull
    public List<ForeignKey<SubscriptionsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_303, Keys.CONSTRAINT_3032);
    }

    private transient Chats _chats;
    private transient Links _links;

    /**
     * Get the implicit join path to the <code>PUBLIC.CHATS</code> table.
     */
    public Chats chats() {
        if (_chats == null)
            _chats = new Chats(this, Keys.CONSTRAINT_303);

        return _chats;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.LINKS</code> table.
     */
    public Links links() {
        if (_links == null)
            _links = new Links(this, Keys.CONSTRAINT_3032);

        return _links;
    }

    @Override
    @NotNull
    public Subscriptions as(String alias) {
        return new Subscriptions(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public Subscriptions as(Name alias) {
        return new Subscriptions(alias, this);
    }

    @Override
    @NotNull
    public Subscriptions as(Table<?> alias) {
        return new Subscriptions(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Subscriptions rename(String name) {
        return new Subscriptions(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Subscriptions rename(Name name) {
        return new Subscriptions(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Subscriptions rename(Table<?> name) {
        return new Subscriptions(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Integer, ? super Integer, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

/*
 * This file is generated by jOOQ.
 */
package edu.java.scrapper.domain.jooq.tables;


import edu.java.scrapper.domain.jooq.DefaultSchema;
import edu.java.scrapper.domain.jooq.Keys;
import edu.java.scrapper.domain.jooq.tables.records.ChatsRecord;

import java.util.function.Function;

import javax.annotation.processing.Generated;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function1;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row1;
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
public class Chats extends TableImpl<ChatsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>CHATS</code>
     */
    public static final Chats CHATS = new Chats();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<ChatsRecord> getRecordType() {
        return ChatsRecord.class;
    }

    /**
     * The column <code>CHATS.ID</code>.
     */
    public final TableField<ChatsRecord, Long> ID = createField(DSL.name("ID"), SQLDataType.BIGINT.nullable(false), this, "");

    private Chats(Name alias, Table<ChatsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Chats(Name alias, Table<ChatsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>CHATS</code> table reference
     */
    public Chats(String alias) {
        this(DSL.name(alias), CHATS);
    }

    /**
     * Create an aliased <code>CHATS</code> table reference
     */
    public Chats(Name alias) {
        this(alias, CHATS);
    }

    /**
     * Create a <code>CHATS</code> table reference
     */
    public Chats() {
        this(DSL.name("CHATS"), null);
    }

    public <O extends Record> Chats(Table<O> child, ForeignKey<O, ChatsRecord> key) {
        super(child, key, CHATS);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public UniqueKey<ChatsRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_3;
    }

    @Override
    @NotNull
    public Chats as(String alias) {
        return new Chats(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public Chats as(Name alias) {
        return new Chats(alias, this);
    }

    @Override
    @NotNull
    public Chats as(Table<?> alias) {
        return new Chats(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Chats rename(String name) {
        return new Chats(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Chats rename(Name name) {
        return new Chats(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public Chats rename(Table<?> name) {
        return new Chats(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row1<Long> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function1<? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function1<? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

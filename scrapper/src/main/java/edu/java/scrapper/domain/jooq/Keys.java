/*
 * This file is generated by jOOQ.
 */

package edu.java.scrapper.domain.jooq;

import edu.java.scrapper.domain.jooq.tables.Chats;
import edu.java.scrapper.domain.jooq.tables.Links;
import edu.java.scrapper.domain.jooq.tables.Subscriptions;
import edu.java.scrapper.domain.jooq.tables.records.ChatsRecord;
import edu.java.scrapper.domain.jooq.tables.records.LinksRecord;
import edu.java.scrapper.domain.jooq.tables.records.SubscriptionsRecord;
import javax.annotation.processing.Generated;
import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

/**
 * A class modelling foreign key relationships and constraints of tables in the
 * default schema.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ChatsRecord> CONSTRAINT_3 =
        Internal.createUniqueKey(Chats.CHATS, DSL.name("CONSTRAINT_3"), new TableField[] {Chats.CHATS.ID}, true);
    public static final UniqueKey<LinksRecord> CONSTRAINT_4 =
        Internal.createUniqueKey(Links.LINKS, DSL.name("CONSTRAINT_4"), new TableField[] {Links.LINKS.ID}, true);
    public static final UniqueKey<LinksRecord> CONSTRAINT_45 =
        Internal.createUniqueKey(Links.LINKS, DSL.name("CONSTRAINT_45"), new TableField[] {Links.LINKS.URL}, true);
    public static final UniqueKey<SubscriptionsRecord> CONSTRAINT_30 =
        Internal.createUniqueKey(
            Subscriptions.SUBSCRIPTIONS,
            DSL.name("CONSTRAINT_30"),
            new TableField[] {Subscriptions.SUBSCRIPTIONS.CHAT_ID, Subscriptions.SUBSCRIPTIONS.LINK_ID},
            true
        );

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<SubscriptionsRecord, ChatsRecord> CONSTRAINT_303 = Internal.createForeignKey(
        Subscriptions.SUBSCRIPTIONS,
        DSL.name("CONSTRAINT_303"),
        new TableField[] {Subscriptions.SUBSCRIPTIONS.CHAT_ID},
        Keys.CONSTRAINT_3,
        new TableField[] {Chats.CHATS.ID},
        true
    );
    public static final ForeignKey<SubscriptionsRecord, LinksRecord> CONSTRAINT_3032 = Internal.createForeignKey(
        Subscriptions.SUBSCRIPTIONS,
        DSL.name("CONSTRAINT_3032"),
        new TableField[] {Subscriptions.SUBSCRIPTIONS.LINK_ID},
        Keys.CONSTRAINT_4,
        new TableField[] {Links.LINKS.ID},
        true
    );
}

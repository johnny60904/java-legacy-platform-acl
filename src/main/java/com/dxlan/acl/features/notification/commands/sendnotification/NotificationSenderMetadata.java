package com.dxlan.acl.features.notification.commands.sendnotification;

import com.dxlan.acl.features.notification.common.metadata.SendNotificationSliceMetadata;
import com.dxlan.acl.features.shared.architectures.*;
import com.dxlan.acl.features.shared.languages.LanguageElement;

record NotificationSenderMetadata(
        boolean isInternalCall
) implements SendNotificationSliceMetadata {

    private static final boolean INTERNAL_PASSPORT = true;

    NotificationSenderMetadata(
            final boolean isInternalCall
    ) {
        if (isInternalCall != INTERNAL_PASSPORT) {
            throw new UnsupportedOperationException(
                    "Architectural metadata is a singleton. Use the Interface's getMetadata() instead."
            );
        }
        this.isInternalCall = isInternalCall;
    }

    private static final SendNotificationSliceMetadata INSTANCE =
            new NotificationSenderMetadata(INTERNAL_PASSPORT);

    static SendNotificationSliceMetadata getInstance() {
        return INSTANCE;
    }

    @Override
    public ArchitecturalScope scope() {
        return ArchitecturalScope.SLICE_COMMAND;
    }

    @Override
    public String systemName() {
        return getSystemName();
    }

    @Override
    public ArchitecturalParadigms paradigms() {
        return ArchitecturalParadigms.TRANSACTION_SCRIPT;
    }

    @Override
    public ArchitecturalStyle style() {
        return ArchitecturalStyle.VERTICAL_SLICE;
    }

    @Override
    public ArchitecturalPattern pattern() {
        return ArchitecturalPattern.CQRS_COMMAND;
    }

    @Override
    public ArchitecturalStereotype stereotype() {
        return ArchitecturalStereotype.CONTRACT;
    }

    @Override
    public LanguageElement languageElement() {
        return LanguageElement.INTERFACE;
    }

    @Override
    public String typeName() {
        return NotificationSender.class.getSimpleName();
    }

}

package com.dirac.acs.datastore;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;

/* loaded from: classes4.dex */
public final class SettingsOuterClass {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Configuration_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Configuration_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Settings_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Settings_fieldAccessorTable;

    private SettingsOuterClass() {
    }

    public static void registerAllExtensions(ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
        registerAllExtensions((ExtensionRegistryLite) registry);
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = {"\n\u000esettings.proto\u0012\u0017com.dirac.acs.datastore\u001a\u0018parameter_messages.proto\u001a\u001fgoogle/protobuf/timestamp.proto\"Ù\u0001\n\bSettings\u0012\f\n\u0004uuid\u0018\u0001 \u0001(\t\u00121\n\rcreation_time\u0018\u0002 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012>\n\u000econfigurations\u0018\u0003 \u0003(\u000b2&.com.dirac.acs.datastore.Configuration\u0012\r\n\u0005isGEF\u0018\u0004 \u0001(\b\u0012\u0016\n\u000eisDiracEnabled\u0018\u0005 \u0001(\b\u0012\u0012\n\noutputType\u0018\u0006 \u0003(\u0005\u0012\u0011\n\tisEnabled\u0018\u0007 \u0003(\b\"º\u0001\n\rConfiguration\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0012\n\noutputType\u0018\u0002 \u0001(\t\u0012\u0012\n\nstreamType\u0018\u0003 \u0001(\t\u00123\n\u0006values\u0018\u0004 \u0003(\u000b2#.com.dirac.acs.datastore.ParamValue\u0012\u000e\n\u0006active\u0018\u0005 \u0001(\b\u0012.\n\nactiveTime\u0018\u0006 \u0001(\u000b2\u001a.google.protobuf.TimestampB\u001d\n\u0017com.dirac.acs.datastoreH\u0001P\u0001b\u0006proto3"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{ParameterMessages.getDescriptor(), TimestampProto.getDescriptor()});
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_com_dirac_acs_datastore_Settings_descriptor = descriptor2;
        internal_static_com_dirac_acs_datastore_Settings_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"Uuid", "CreationTime", "Configurations", "IsGEF", "IsDiracEnabled", "OutputType", "IsEnabled"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_com_dirac_acs_datastore_Configuration_descriptor = descriptor3;
        internal_static_com_dirac_acs_datastore_Configuration_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Name", "OutputType", "StreamType", "Values", "Active", "ActiveTime"});
        ParameterMessages.getDescriptor();
        TimestampProto.getDescriptor();
    }
}

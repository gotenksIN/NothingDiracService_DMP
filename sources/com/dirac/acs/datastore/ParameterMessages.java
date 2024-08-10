package com.dirac.acs.datastore;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;

/* loaded from: classes4.dex */
public final class ParameterMessages {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Id_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Id_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Ids_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Ids_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_ParamEnumValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_ParamEnumValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_ParamInfo_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_ParamInfo_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_ParamNotify_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_ParamNotify_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_ParamValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_ParamValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_ParamValues_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_ParamValues_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_ParamVoid_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_ParamVoid_fieldAccessorTable;

    private ParameterMessages() {
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
        String[] descriptorData = {"\n\u0018parameter_messages.proto\u0012\u0017com.dirac.acs.datastore\"\u000b\n\tParamVoid\"\u0010\n\u0002Id\u0012\n\n\u0002id\u0018\u0001 \u0001(\u0011\"-\n\u000eParamEnumValue\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0001\"/\n\u0003Ids\u0012(\n\u0003ids\u0018\u0001 \u0003(\u000b2\u001b.com.dirac.acs.datastore.Id\"§\u0002\n\tParamInfo\u0012'\n\u0002id\u0018\u0001 \u0001(\u000b2\u001b.com.dirac.acs.datastore.Id\u0012\f\n\u0004name\u0018\u0002 \u0001(\t\u0012\u0013\n\u000bdescription\u0018\u0003 \u0001(\t\u0012\u000b\n\u0003min\u0018\u0004 \u0001(\u0001\u0012\u000b\n\u0003max\u0018\u0005 \u0001(\u0001\u0012\u000f\n\u0007default\u0018\u0006 \u0001(\u0001\u0012\u0011\n\tstep_size\u0018\u0007 \u0001(\u0001\u00120\n\u0004type\u0018\b \u0001(\u000e2\".com.dirac.acs.datastore.ParamType\u0012<\n\u000benum_values\u0018\t \u0003(\u000b2'.com.dirac.acs.datastore.ParamEnumValue\u0012\f\n\u0004unit\u0018\n \u0001(\t\u0012\u0012\n\nformatting\u0018\u000b \u0001(\t\"U\n\nParamValue\u0012'\n\u0002id\u0018\u0001 \u0001(\u000b2\u001b.com.dirac.acs.datastore.Id\u0012\r\n\u0005value\u0018\u0002 \u0001(\u0001\u0012\u000f\n\u0007updated\u0018\u0003 \u0001(\b\"B\n\u000bParamValues\u00123\n\u0006values\u0018\u0001 \u0003(\u000b2#.com.dirac.acs.datastore.ParamValue\"K\n\u000bParamNotify\u0012\u0011\n\tchange_id\u0018\u0001 \u0001(\t\u0012)\n\u0003ids\u0018\u0002 \u0001(\u000b2\u001c.com.dirac.acs.datastore.Ids*q\n\tParamType\u0012\u0015\n\u0011PARAM_TYPE_LINEAR\u0010\u0000\u0012\u001a\n\u0016PARAM_TYPE_LOGARITHMIC\u0010\u0001\u0012\u0015\n\u0011PARAM_TYPE_TOGGLE\u0010\u0002\u0012\u001a\n\u0016PARAM_TYPE_ENUMERATION\u0010\u0003B\u001d\n\u0017com.dirac.acs.datastoreH\u0001P\u0001b\u0006proto3"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_com_dirac_acs_datastore_ParamVoid_descriptor = descriptor2;
        internal_static_com_dirac_acs_datastore_ParamVoid_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[0]);
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_com_dirac_acs_datastore_Id_descriptor = descriptor3;
        internal_static_com_dirac_acs_datastore_Id_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Id"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_com_dirac_acs_datastore_ParamEnumValue_descriptor = descriptor4;
        internal_static_com_dirac_acs_datastore_ParamEnumValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Name", "Value"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_com_dirac_acs_datastore_Ids_descriptor = descriptor5;
        internal_static_com_dirac_acs_datastore_Ids_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Ids"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_com_dirac_acs_datastore_ParamInfo_descriptor = descriptor6;
        internal_static_com_dirac_acs_datastore_ParamInfo_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Id", "Name", "Description", "Min", "Max", "Default", "StepSize", "Type", "EnumValues", "Unit", "Formatting"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_com_dirac_acs_datastore_ParamValue_descriptor = descriptor7;
        internal_static_com_dirac_acs_datastore_ParamValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Id", "Value", "Updated"});
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(6);
        internal_static_com_dirac_acs_datastore_ParamValues_descriptor = descriptor8;
        internal_static_com_dirac_acs_datastore_ParamValues_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Values"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(7);
        internal_static_com_dirac_acs_datastore_ParamNotify_descriptor = descriptor9;
        internal_static_com_dirac_acs_datastore_ParamNotify_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"ChangeId", "Ids"});
    }
}

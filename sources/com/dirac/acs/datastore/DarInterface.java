package com.dirac.acs.datastore;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;

/* loaded from: classes4.dex */
public final class DarInterface {
    private static Descriptors.FileDescriptor descriptor;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Archive_ArchiveMetaDataEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Archive_ArchiveMetaDataEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Archive_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Archive_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_SignalChain_SignalChainMetaDataEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_SignalChain_SignalChainMetaDataEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_SignalChain_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_SignalChain_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Value_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Value_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_com_dirac_acs_datastore_Version_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_com_dirac_acs_datastore_Version_fieldAccessorTable;

    private DarInterface() {
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
        String[] descriptorData = {"\n\u0013dar_interface.proto\u0012\u0017com.dirac.acs.datastore\u001a\u0018parameter_messages.proto\u001a\u001fgoogle/protobuf/timestamp.proto\"N\n\u0007Version\u0012\u0015\n\rmajor_version\u0018\u0001 \u0001(\u0005\u0012\u0015\n\rminor_version\u0018\u0002 \u0001(\u0005\u0012\u0015\n\rpatch_version\u0018\u0003 \u0001(\u0005\"\u009b\u0001\n\u0005Value\u0012\u0016\n\fstring_value\u0018\u0001 \u0001(\tH\u0000\u0012\u0015\n\u000bfloat_value\u0018\u0002 \u0001(\u0002H\u0000\u0012\u0015\n\u000bint32_value\u0018\u0003 \u0001(\u0005H\u0000\u0012\u0015\n\u000bint64_value\u0018\u0004 \u0001(\u0003H\u0000\u0012\u0014\n\nbool_value\u0018\u0005 \u0001(\bH\u0000\u0012\u0016\n\fbinary_value\u0018\u0006 \u0001(\fH\u0000B\u0007\n\u0005value\"\u0087\u0004\n\u0007Archive\u0012;\n\u0011interface_version\u0018\u0001 \u0001(\u000b2 .com.dirac.acs.datastore.Version\u0012\f\n\u0004uuid\u0018\u0002 \u0001(\t\u0012P\n\u0011archive_meta_data\u0018\u0003 \u0003(\u000b25.com.dirac.acs.datastore.Archive.ArchiveMetaDataEntry\u00121\n\rcreation_time\u0018\u0004 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012\u0013\n\u000bdescription\u0018\u0005 \u0001(\t\u0012<\n\u000econfigurations\u0018\u0006 \u0003(\u000b2$.com.dirac.acs.datastore.SignalChain\u0012\u000f\n\u0007creator\u0018\u0007 \u0001(\t\u00129\n\u000fcreator_version\u0018\b \u0001(\u000b2 .com.dirac.acs.datastore.Version\u0012\u000e\n\u0006author\u0018\t \u0001(\t\u0012\u000f\n\u0007project\u0018\n \u0001(\t\u0012\u0014\n\fmanufacturer\u0018\u000b \u0001(\t\u001aV\n\u0014ArchiveMetaDataEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012-\n\u0005value\u0018\u0002 \u0001(\u000b2\u001e.com.dirac.acs.datastore.Value:\u00028\u0001\"É\u0002\n\u000bSignalChain\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00129\n\u000frt_core_version\u0018\u0002 \u0001(\u000b2 .com.dirac.acs.datastore.Version\u0012]\n\u0016signal_chain_meta_data\u0018\u0003 \u0003(\u000b2=.com.dirac.acs.datastore.SignalChain.SignalChainMetaDataEntry\u00126\n\nparameters\u0018\u0004 \u0003(\u000b2\".com.dirac.acs.datastore.ParamInfo\u001aZ\n\u0018SignalChainMetaDataEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012-\n\u0005value\u0018\u0002 \u0001(\u000b2\u001e.com.dirac.acs.datastore.Value:\u00028\u0001B\u001d\n\u0017com.dirac.acs.datastoreH\u0001P\u0001b\u0006proto3"};
        descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[]{ParameterMessages.getDescriptor(), TimestampProto.getDescriptor()});
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_com_dirac_acs_datastore_Version_descriptor = descriptor2;
        internal_static_com_dirac_acs_datastore_Version_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"MajorVersion", "MinorVersion", "PatchVersion"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_com_dirac_acs_datastore_Value_descriptor = descriptor3;
        internal_static_com_dirac_acs_datastore_Value_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"StringValue", "FloatValue", "Int32Value", "Int64Value", "BoolValue", "BinaryValue", "Value"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_com_dirac_acs_datastore_Archive_descriptor = descriptor4;
        internal_static_com_dirac_acs_datastore_Archive_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"InterfaceVersion", "Uuid", "ArchiveMetaData", "CreationTime", "Description", "Configurations", "Creator", "CreatorVersion", "Author", "Project", "Manufacturer"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor4.getNestedTypes().get(0);
        internal_static_com_dirac_acs_datastore_Archive_ArchiveMetaDataEntry_descriptor = descriptor5;
        internal_static_com_dirac_acs_datastore_Archive_ArchiveMetaDataEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_com_dirac_acs_datastore_SignalChain_descriptor = descriptor6;
        internal_static_com_dirac_acs_datastore_SignalChain_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Name", "RtCoreVersion", "SignalChainMetaData", "Parameters"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor6.getNestedTypes().get(0);
        internal_static_com_dirac_acs_datastore_SignalChain_SignalChainMetaDataEntry_descriptor = descriptor7;
        internal_static_com_dirac_acs_datastore_SignalChain_SignalChainMetaDataEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Key", "Value"});
        ParameterMessages.getDescriptor();
        TimestampProto.getDescriptor();
    }
}

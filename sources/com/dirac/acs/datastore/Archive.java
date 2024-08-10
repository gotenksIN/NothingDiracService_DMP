package com.dirac.acs.datastore;

import com.dirac.acs.datastore.SignalChain;
import com.dirac.acs.datastore.Version;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class Archive extends GeneratedMessageV3 implements ArchiveOrBuilder {
    public static final int ARCHIVE_META_DATA_FIELD_NUMBER = 3;
    public static final int AUTHOR_FIELD_NUMBER = 9;
    public static final int CONFIGURATIONS_FIELD_NUMBER = 6;
    public static final int CREATION_TIME_FIELD_NUMBER = 4;
    public static final int CREATOR_FIELD_NUMBER = 7;
    public static final int CREATOR_VERSION_FIELD_NUMBER = 8;
    public static final int DESCRIPTION_FIELD_NUMBER = 5;
    public static final int INTERFACE_VERSION_FIELD_NUMBER = 1;
    public static final int MANUFACTURER_FIELD_NUMBER = 11;
    public static final int PROJECT_FIELD_NUMBER = 10;
    public static final int UUID_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private MapField<String, Value> archiveMetaData_;
    private volatile Object author_;
    private List<SignalChain> configurations_;
    private Timestamp creationTime_;
    private Version creatorVersion_;
    private volatile Object creator_;
    private volatile Object description_;
    private Version interfaceVersion_;
    private volatile Object manufacturer_;
    private byte memoizedIsInitialized;
    private volatile Object project_;
    private volatile Object uuid_;
    private static final Archive DEFAULT_INSTANCE = new Archive();
    private static final Parser<Archive> PARSER = new AbstractParser<Archive>() { // from class: com.dirac.acs.datastore.Archive.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Archive m98parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Archive(input, extensionRegistry);
        }
    };

    private Archive(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Archive() {
        this.memoizedIsInitialized = (byte) -1;
        this.uuid_ = "";
        this.description_ = "";
        this.configurations_ = Collections.emptyList();
        this.creator_ = "";
        this.author_ = "";
        this.project_ = "";
        this.manufacturer_ = "";
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new Archive();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0011. Please report as an issue. */
    private Archive(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        boolean done = false;
        while (!done) {
            try {
                try {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                        case 10:
                            Version version = this.interfaceVersion_;
                            Version.Builder subBuilder = version != null ? version.m775toBuilder() : null;
                            Version version2 = (Version) input.readMessage(Version.parser(), extensionRegistry);
                            this.interfaceVersion_ = version2;
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(version2);
                                this.interfaceVersion_ = subBuilder.m782buildPartial();
                            }
                        case 18:
                            String s = input.readStringRequireUtf8();
                            this.uuid_ = s;
                        case 26:
                            if ((mutable_bitField0_ & 1) == 0) {
                                this.archiveMetaData_ = MapField.newMapField(ArchiveMetaDataDefaultEntryHolder.defaultEntry);
                                mutable_bitField0_ |= 1;
                            }
                            MapEntry<String, Value> archiveMetaData__ = input.readMessage(ArchiveMetaDataDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
                            this.archiveMetaData_.getMutableMap().put((String) archiveMetaData__.getKey(), (Value) archiveMetaData__.getValue());
                        case 34:
                            Timestamp timestamp = this.creationTime_;
                            Timestamp.Builder subBuilder2 = timestamp != null ? timestamp.toBuilder() : null;
                            Timestamp readMessage = input.readMessage(Timestamp.parser(), extensionRegistry);
                            this.creationTime_ = readMessage;
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom(readMessage);
                                this.creationTime_ = subBuilder2.buildPartial();
                            }
                        case 42:
                            String s2 = input.readStringRequireUtf8();
                            this.description_ = s2;
                        case 50:
                            if ((mutable_bitField0_ & 2) == 0) {
                                this.configurations_ = new ArrayList();
                                mutable_bitField0_ |= 2;
                            }
                            this.configurations_.add((SignalChain) input.readMessage(SignalChain.parser(), extensionRegistry));
                        case 58:
                            String s3 = input.readStringRequireUtf8();
                            this.creator_ = s3;
                        case 66:
                            Version version3 = this.creatorVersion_;
                            Version.Builder subBuilder3 = version3 != null ? version3.m775toBuilder() : null;
                            Version version4 = (Version) input.readMessage(Version.parser(), extensionRegistry);
                            this.creatorVersion_ = version4;
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom(version4);
                                this.creatorVersion_ = subBuilder3.m782buildPartial();
                            }
                        case 74:
                            String s4 = input.readStringRequireUtf8();
                            this.author_ = s4;
                        case 82:
                            String s5 = input.readStringRequireUtf8();
                            this.project_ = s5;
                        case 90:
                            String s6 = input.readStringRequireUtf8();
                            this.manufacturer_ = s6;
                        default:
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                    }
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((mutable_bitField0_ & 2) != 0) {
                    this.configurations_ = Collections.unmodifiableList(this.configurations_);
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return DarInterface.internal_static_com_dirac_acs_datastore_Archive_descriptor;
    }

    protected MapField internalGetMapField(int number) {
        switch (number) {
            case 3:
                return internalGetArchiveMetaData();
            default:
                throw new RuntimeException("Invalid map field number: " + number);
        }
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return DarInterface.internal_static_com_dirac_acs_datastore_Archive_fieldAccessorTable.ensureFieldAccessorsInitialized(Archive.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public boolean hasInterfaceVersion() {
        return this.interfaceVersion_ != null;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public Version getInterfaceVersion() {
        Version version = this.interfaceVersion_;
        return version == null ? Version.getDefaultInstance() : version;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public VersionOrBuilder getInterfaceVersionOrBuilder() {
        return getInterfaceVersion();
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public String getUuid() {
        Object ref = this.uuid_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.uuid_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public ByteString getUuidBytes() {
        Object ref = this.uuid_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.uuid_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static final class ArchiveMetaDataDefaultEntryHolder {
        static final MapEntry<String, Value> defaultEntry = MapEntry.newDefaultInstance(DarInterface.internal_static_com_dirac_acs_datastore_Archive_ArchiveMetaDataEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

        private ArchiveMetaDataDefaultEntryHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Value> internalGetArchiveMetaData() {
        MapField<String, Value> mapField = this.archiveMetaData_;
        if (mapField == null) {
            return MapField.emptyMapField(ArchiveMetaDataDefaultEntryHolder.defaultEntry);
        }
        return mapField;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public int getArchiveMetaDataCount() {
        return internalGetArchiveMetaData().getMap().size();
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public boolean containsArchiveMetaData(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return internalGetArchiveMetaData().getMap().containsKey(key);
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    @Deprecated
    public Map<String, Value> getArchiveMetaData() {
        return getArchiveMetaDataMap();
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public Map<String, Value> getArchiveMetaDataMap() {
        return internalGetArchiveMetaData().getMap();
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public Value getArchiveMetaDataOrDefault(String key, Value defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, Value> map = internalGetArchiveMetaData().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public Value getArchiveMetaDataOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        Map<String, Value> map = internalGetArchiveMetaData().getMap();
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map.get(key);
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public boolean hasCreationTime() {
        return this.creationTime_ != null;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public Timestamp getCreationTime() {
        Timestamp timestamp = this.creationTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public TimestampOrBuilder getCreationTimeOrBuilder() {
        return getCreationTime();
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public String getDescription() {
        Object ref = this.description_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.description_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public ByteString getDescriptionBytes() {
        Object ref = this.description_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.description_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public List<SignalChain> getConfigurationsList() {
        return this.configurations_;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public List<? extends SignalChainOrBuilder> getConfigurationsOrBuilderList() {
        return this.configurations_;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public int getConfigurationsCount() {
        return this.configurations_.size();
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public SignalChain getConfigurations(int index) {
        return this.configurations_.get(index);
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public SignalChainOrBuilder getConfigurationsOrBuilder(int index) {
        return this.configurations_.get(index);
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public String getCreator() {
        Object ref = this.creator_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.creator_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public ByteString getCreatorBytes() {
        Object ref = this.creator_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.creator_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public boolean hasCreatorVersion() {
        return this.creatorVersion_ != null;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public Version getCreatorVersion() {
        Version version = this.creatorVersion_;
        return version == null ? Version.getDefaultInstance() : version;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public VersionOrBuilder getCreatorVersionOrBuilder() {
        return getCreatorVersion();
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public String getAuthor() {
        Object ref = this.author_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.author_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public ByteString getAuthorBytes() {
        Object ref = this.author_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.author_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public String getProject() {
        Object ref = this.project_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.project_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public ByteString getProjectBytes() {
        Object ref = this.project_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.project_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public String getManufacturer() {
        Object ref = this.manufacturer_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.manufacturer_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ArchiveOrBuilder
    public ByteString getManufacturerBytes() {
        Object ref = this.manufacturer_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.manufacturer_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public void writeTo(CodedOutputStream output) throws IOException {
        if (this.interfaceVersion_ != null) {
            output.writeMessage(1, getInterfaceVersion());
        }
        if (!getUuidBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 2, this.uuid_);
        }
        GeneratedMessageV3.serializeStringMapTo(output, internalGetArchiveMetaData(), ArchiveMetaDataDefaultEntryHolder.defaultEntry, 3);
        if (this.creationTime_ != null) {
            output.writeMessage(4, getCreationTime());
        }
        if (!getDescriptionBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 5, this.description_);
        }
        for (int i = 0; i < this.configurations_.size(); i++) {
            output.writeMessage(6, this.configurations_.get(i));
        }
        if (!getCreatorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 7, this.creator_);
        }
        if (this.creatorVersion_ != null) {
            output.writeMessage(8, getCreatorVersion());
        }
        if (!getAuthorBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 9, this.author_);
        }
        if (!getProjectBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 10, this.project_);
        }
        if (!getManufacturerBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 11, this.manufacturer_);
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = this.interfaceVersion_ != null ? 0 + CodedOutputStream.computeMessageSize(1, getInterfaceVersion()) : 0;
        if (!getUuidBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(2, this.uuid_);
        }
        for (Map.Entry<String, Value> entry : internalGetArchiveMetaData().getMap().entrySet()) {
            MapEntry<String, Value> archiveMetaData__ = ArchiveMetaDataDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
            size2 += CodedOutputStream.computeMessageSize(3, archiveMetaData__);
        }
        if (this.creationTime_ != null) {
            size2 += CodedOutputStream.computeMessageSize(4, getCreationTime());
        }
        if (!getDescriptionBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(5, this.description_);
        }
        for (int i = 0; i < this.configurations_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(6, this.configurations_.get(i));
        }
        if (!getCreatorBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(7, this.creator_);
        }
        if (this.creatorVersion_ != null) {
            size2 += CodedOutputStream.computeMessageSize(8, getCreatorVersion());
        }
        if (!getAuthorBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(9, this.author_);
        }
        if (!getProjectBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(10, this.project_);
        }
        if (!getManufacturerBytes().isEmpty()) {
            size2 += GeneratedMessageV3.computeStringSize(11, this.manufacturer_);
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Archive)) {
            return super.equals(obj);
        }
        Archive other = (Archive) obj;
        if (hasInterfaceVersion() != other.hasInterfaceVersion()) {
            return false;
        }
        if ((hasInterfaceVersion() && !getInterfaceVersion().equals(other.getInterfaceVersion())) || !getUuid().equals(other.getUuid()) || !internalGetArchiveMetaData().equals(other.internalGetArchiveMetaData()) || hasCreationTime() != other.hasCreationTime()) {
            return false;
        }
        if ((!hasCreationTime() || getCreationTime().equals(other.getCreationTime())) && getDescription().equals(other.getDescription()) && getConfigurationsList().equals(other.getConfigurationsList()) && getCreator().equals(other.getCreator()) && hasCreatorVersion() == other.hasCreatorVersion()) {
            return (!hasCreatorVersion() || getCreatorVersion().equals(other.getCreatorVersion())) && getAuthor().equals(other.getAuthor()) && getProject().equals(other.getProject()) && getManufacturer().equals(other.getManufacturer()) && this.unknownFields.equals(other.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (41 * 19) + getDescriptor().hashCode();
        if (hasInterfaceVersion()) {
            hash = (((hash * 37) + 1) * 53) + getInterfaceVersion().hashCode();
        }
        int hash2 = (((hash * 37) + 2) * 53) + getUuid().hashCode();
        if (!internalGetArchiveMetaData().getMap().isEmpty()) {
            hash2 = (((hash2 * 37) + 3) * 53) + internalGetArchiveMetaData().hashCode();
        }
        if (hasCreationTime()) {
            hash2 = (((hash2 * 37) + 4) * 53) + getCreationTime().hashCode();
        }
        int hash3 = (((hash2 * 37) + 5) * 53) + getDescription().hashCode();
        if (getConfigurationsCount() > 0) {
            hash3 = (((hash3 * 37) + 6) * 53) + getConfigurationsList().hashCode();
        }
        int hash4 = (((hash3 * 37) + 7) * 53) + getCreator().hashCode();
        if (hasCreatorVersion()) {
            hash4 = (((hash4 * 37) + 8) * 53) + getCreatorVersion().hashCode();
        }
        int hash5 = (((((((((((((hash4 * 37) + 9) * 53) + getAuthor().hashCode()) * 37) + 10) * 53) + getProject().hashCode()) * 37) + 11) * 53) + getManufacturer().hashCode()) * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash5;
        return hash5;
    }

    public static Archive parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Archive) PARSER.parseFrom(data);
    }

    public static Archive parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Archive) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Archive parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Archive) PARSER.parseFrom(data);
    }

    public static Archive parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Archive) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Archive parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Archive) PARSER.parseFrom(data);
    }

    public static Archive parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Archive) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Archive parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Archive parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Archive parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static Archive parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static Archive parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Archive parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m95newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m97toBuilder();
    }

    public static Builder newBuilder(Archive prototype) {
        return DEFAULT_INSTANCE.m97toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m97toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m94newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ArchiveOrBuilder {
        private MapField<String, Value> archiveMetaData_;
        private Object author_;
        private int bitField0_;
        private RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> configurationsBuilder_;
        private List<SignalChain> configurations_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> creationTimeBuilder_;
        private Timestamp creationTime_;
        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> creatorVersionBuilder_;
        private Version creatorVersion_;
        private Object creator_;
        private Object description_;
        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> interfaceVersionBuilder_;
        private Version interfaceVersion_;
        private Object manufacturer_;
        private Object project_;
        private Object uuid_;

        public static final Descriptors.Descriptor getDescriptor() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Archive_descriptor;
        }

        protected MapField internalGetMapField(int number) {
            switch (number) {
                case 3:
                    return internalGetArchiveMetaData();
                default:
                    throw new RuntimeException("Invalid map field number: " + number);
            }
        }

        protected MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 3:
                    return internalGetMutableArchiveMetaData();
                default:
                    throw new RuntimeException("Invalid map field number: " + number);
            }
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Archive_fieldAccessorTable.ensureFieldAccessorsInitialized(Archive.class, Builder.class);
        }

        private Builder() {
            this.uuid_ = "";
            this.description_ = "";
            this.configurations_ = Collections.emptyList();
            this.creator_ = "";
            this.author_ = "";
            this.project_ = "";
            this.manufacturer_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.uuid_ = "";
            this.description_ = "";
            this.configurations_ = Collections.emptyList();
            this.creator_ = "";
            this.author_ = "";
            this.project_ = "";
            this.manufacturer_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Archive.alwaysUseFieldBuilders) {
                getConfigurationsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m108clear() {
            super.clear();
            if (this.interfaceVersionBuilder_ == null) {
                this.interfaceVersion_ = null;
            } else {
                this.interfaceVersion_ = null;
                this.interfaceVersionBuilder_ = null;
            }
            this.uuid_ = "";
            internalGetMutableArchiveMetaData().clear();
            if (this.creationTimeBuilder_ == null) {
                this.creationTime_ = null;
            } else {
                this.creationTime_ = null;
                this.creationTimeBuilder_ = null;
            }
            this.description_ = "";
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.configurations_ = Collections.emptyList();
                this.bitField0_ &= -3;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.creator_ = "";
            if (this.creatorVersionBuilder_ == null) {
                this.creatorVersion_ = null;
            } else {
                this.creatorVersion_ = null;
                this.creatorVersionBuilder_ = null;
            }
            this.author_ = "";
            this.project_ = "";
            this.manufacturer_ = "";
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return DarInterface.internal_static_com_dirac_acs_datastore_Archive_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Archive m121getDefaultInstanceForType() {
            return Archive.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Archive m102build() {
            Archive result = m104buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Archive m104buildPartial() {
            Archive result = new Archive(this);
            int i = this.bitField0_;
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.interfaceVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                result.interfaceVersion_ = this.interfaceVersion_;
            } else {
                result.interfaceVersion_ = singleFieldBuilderV3.build();
            }
            result.uuid_ = this.uuid_;
            result.archiveMetaData_ = internalGetArchiveMetaData();
            result.archiveMetaData_.makeImmutable();
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV32 = this.creationTimeBuilder_;
            if (singleFieldBuilderV32 == null) {
                result.creationTime_ = this.creationTime_;
            } else {
                result.creationTime_ = singleFieldBuilderV32.build();
            }
            result.description_ = this.description_;
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 2) != 0) {
                    this.configurations_ = Collections.unmodifiableList(this.configurations_);
                    this.bitField0_ &= -3;
                }
                result.configurations_ = this.configurations_;
            } else {
                result.configurations_ = repeatedFieldBuilderV3.build();
            }
            result.creator_ = this.creator_;
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV33 = this.creatorVersionBuilder_;
            if (singleFieldBuilderV33 == null) {
                result.creatorVersion_ = this.creatorVersion_;
            } else {
                result.creatorVersion_ = singleFieldBuilderV33.build();
            }
            result.author_ = this.author_;
            result.project_ = this.project_;
            result.manufacturer_ = this.manufacturer_;
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m119clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m132setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m110clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m113clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m134setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m100addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m126mergeFrom(Message other) {
            if (other instanceof Archive) {
                return mergeFrom((Archive) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Archive other) {
            if (other == Archive.getDefaultInstance()) {
                return this;
            }
            if (other.hasInterfaceVersion()) {
                mergeInterfaceVersion(other.getInterfaceVersion());
            }
            if (!other.getUuid().isEmpty()) {
                this.uuid_ = other.uuid_;
                onChanged();
            }
            internalGetMutableArchiveMetaData().mergeFrom(other.internalGetArchiveMetaData());
            if (other.hasCreationTime()) {
                mergeCreationTime(other.getCreationTime());
            }
            if (!other.getDescription().isEmpty()) {
                this.description_ = other.description_;
                onChanged();
            }
            if (this.configurationsBuilder_ == null) {
                if (!other.configurations_.isEmpty()) {
                    if (this.configurations_.isEmpty()) {
                        this.configurations_ = other.configurations_;
                        this.bitField0_ &= -3;
                    } else {
                        ensureConfigurationsIsMutable();
                        this.configurations_.addAll(other.configurations_);
                    }
                    onChanged();
                }
            } else if (!other.configurations_.isEmpty()) {
                if (this.configurationsBuilder_.isEmpty()) {
                    this.configurationsBuilder_.dispose();
                    RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = null;
                    this.configurationsBuilder_ = null;
                    this.configurations_ = other.configurations_;
                    this.bitField0_ &= -3;
                    if (Archive.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getConfigurationsFieldBuilder();
                    }
                    this.configurationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.configurationsBuilder_.addAllMessages(other.configurations_);
                }
            }
            if (!other.getCreator().isEmpty()) {
                this.creator_ = other.creator_;
                onChanged();
            }
            if (other.hasCreatorVersion()) {
                mergeCreatorVersion(other.getCreatorVersion());
            }
            if (!other.getAuthor().isEmpty()) {
                this.author_ = other.author_;
                onChanged();
            }
            if (!other.getProject().isEmpty()) {
                this.project_ = other.project_;
                onChanged();
            }
            if (!other.getManufacturer().isEmpty()) {
                this.manufacturer_ = other.manufacturer_;
                onChanged();
            }
            m130mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m127mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Archive parsedMessage = null;
            try {
                try {
                    parsedMessage = (Archive) Archive.PARSER.parsePartialFrom(input, extensionRegistry);
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public boolean hasInterfaceVersion() {
            return (this.interfaceVersionBuilder_ == null && this.interfaceVersion_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public Version getInterfaceVersion() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.interfaceVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.interfaceVersion_;
                return version == null ? Version.getDefaultInstance() : version;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setInterfaceVersion(Version value) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.interfaceVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.interfaceVersion_ = value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(value);
            }
            return this;
        }

        public Builder setInterfaceVersion(Version.Builder builderForValue) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.interfaceVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.interfaceVersion_ = builderForValue.m780build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builderForValue.m780build());
            }
            return this;
        }

        public Builder mergeInterfaceVersion(Version value) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.interfaceVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.interfaceVersion_;
                if (version != null) {
                    this.interfaceVersion_ = Version.newBuilder(version).mergeFrom(value).m782buildPartial();
                } else {
                    this.interfaceVersion_ = value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(value);
            }
            return this;
        }

        public Builder clearInterfaceVersion() {
            if (this.interfaceVersionBuilder_ == null) {
                this.interfaceVersion_ = null;
                onChanged();
            } else {
                this.interfaceVersion_ = null;
                this.interfaceVersionBuilder_ = null;
            }
            return this;
        }

        public Version.Builder getInterfaceVersionBuilder() {
            onChanged();
            return getInterfaceVersionFieldBuilder().getBuilder();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public VersionOrBuilder getInterfaceVersionOrBuilder() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.interfaceVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (VersionOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Version version = this.interfaceVersion_;
            if (version != null) {
                return version;
            }
            return Version.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getInterfaceVersionFieldBuilder() {
            if (this.interfaceVersionBuilder_ == null) {
                this.interfaceVersionBuilder_ = new SingleFieldBuilderV3<>(getInterfaceVersion(), getParentForChildren(), isClean());
                this.interfaceVersion_ = null;
            }
            return this.interfaceVersionBuilder_;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public String getUuid() {
            Object ref = this.uuid_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.uuid_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public ByteString getUuidBytes() {
            Object ref = this.uuid_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.uuid_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setUuid(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.uuid_ = value;
            onChanged();
            return this;
        }

        public Builder clearUuid() {
            this.uuid_ = Archive.getDefaultInstance().getUuid();
            onChanged();
            return this;
        }

        public Builder setUuidBytes(ByteString value) {
            if (value != null) {
                Archive.checkByteStringIsUtf8(value);
                this.uuid_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private MapField<String, Value> internalGetArchiveMetaData() {
            MapField<String, Value> mapField = this.archiveMetaData_;
            if (mapField == null) {
                return MapField.emptyMapField(ArchiveMetaDataDefaultEntryHolder.defaultEntry);
            }
            return mapField;
        }

        private MapField<String, Value> internalGetMutableArchiveMetaData() {
            onChanged();
            if (this.archiveMetaData_ == null) {
                this.archiveMetaData_ = MapField.newMapField(ArchiveMetaDataDefaultEntryHolder.defaultEntry);
            }
            if (!this.archiveMetaData_.isMutable()) {
                this.archiveMetaData_ = this.archiveMetaData_.copy();
            }
            return this.archiveMetaData_;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public int getArchiveMetaDataCount() {
            return internalGetArchiveMetaData().getMap().size();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public boolean containsArchiveMetaData(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return internalGetArchiveMetaData().getMap().containsKey(key);
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        @Deprecated
        public Map<String, Value> getArchiveMetaData() {
            return getArchiveMetaDataMap();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public Map<String, Value> getArchiveMetaDataMap() {
            return internalGetArchiveMetaData().getMap();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public Value getArchiveMetaDataOrDefault(String key, Value defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, Value> map = internalGetArchiveMetaData().getMap();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public Value getArchiveMetaDataOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            Map<String, Value> map = internalGetArchiveMetaData().getMap();
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearArchiveMetaData() {
            internalGetMutableArchiveMetaData().getMutableMap().clear();
            return this;
        }

        public Builder removeArchiveMetaData(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            internalGetMutableArchiveMetaData().getMutableMap().remove(key);
            return this;
        }

        @Deprecated
        public Map<String, Value> getMutableArchiveMetaData() {
            return internalGetMutableArchiveMetaData().getMutableMap();
        }

        public Builder putArchiveMetaData(String key, Value value) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (value == null) {
                throw new NullPointerException();
            }
            internalGetMutableArchiveMetaData().getMutableMap().put(key, value);
            return this;
        }

        public Builder putAllArchiveMetaData(Map<String, Value> values) {
            internalGetMutableArchiveMetaData().getMutableMap().putAll(values);
            return this;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public boolean hasCreationTime() {
            return (this.creationTimeBuilder_ == null && this.creationTime_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public Timestamp getCreationTime() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.creationTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp = this.creationTime_;
                return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setCreationTime(Timestamp value) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.creationTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.creationTime_ = value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(value);
            }
            return this;
        }

        public Builder setCreationTime(Timestamp.Builder builderForValue) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.creationTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.creationTime_ = builderForValue.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builderForValue.build());
            }
            return this;
        }

        public Builder mergeCreationTime(Timestamp value) {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.creationTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                Timestamp timestamp = this.creationTime_;
                if (timestamp != null) {
                    this.creationTime_ = Timestamp.newBuilder(timestamp).mergeFrom(value).buildPartial();
                } else {
                    this.creationTime_ = value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(value);
            }
            return this;
        }

        public Builder clearCreationTime() {
            if (this.creationTimeBuilder_ == null) {
                this.creationTime_ = null;
                onChanged();
            } else {
                this.creationTime_ = null;
                this.creationTimeBuilder_ = null;
            }
            return this;
        }

        public Timestamp.Builder getCreationTimeBuilder() {
            onChanged();
            return getCreationTimeFieldBuilder().getBuilder();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public TimestampOrBuilder getCreationTimeOrBuilder() {
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.creationTimeBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Timestamp timestamp = this.creationTime_;
            if (timestamp != null) {
                return timestamp;
            }
            return Timestamp.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> getCreationTimeFieldBuilder() {
            if (this.creationTimeBuilder_ == null) {
                this.creationTimeBuilder_ = new SingleFieldBuilderV3<>(getCreationTime(), getParentForChildren(), isClean());
                this.creationTime_ = null;
            }
            return this.creationTimeBuilder_;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public String getDescription() {
            Object ref = this.description_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.description_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public ByteString getDescriptionBytes() {
            Object ref = this.description_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.description_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setDescription(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.description_ = value;
            onChanged();
            return this;
        }

        public Builder clearDescription() {
            this.description_ = Archive.getDefaultInstance().getDescription();
            onChanged();
            return this;
        }

        public Builder setDescriptionBytes(ByteString value) {
            if (value != null) {
                Archive.checkByteStringIsUtf8(value);
                this.description_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        private void ensureConfigurationsIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.configurations_ = new ArrayList(this.configurations_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public List<SignalChain> getConfigurationsList() {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.configurations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public int getConfigurationsCount() {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.configurations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public SignalChain getConfigurations(int index) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.configurations_.get(index);
            }
            return repeatedFieldBuilderV3.getMessage(index);
        }

        public Builder setConfigurations(int index, SignalChain value) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureConfigurationsIsMutable();
                this.configurations_.set(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, value);
            }
            return this;
        }

        public Builder setConfigurations(int index, SignalChain.Builder builderForValue) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.set(index, builderForValue.m680build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, builderForValue.m680build());
            }
            return this;
        }

        public Builder addConfigurations(SignalChain value) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureConfigurationsIsMutable();
                this.configurations_.add(value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(value);
            }
            return this;
        }

        public Builder addConfigurations(int index, SignalChain value) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                ensureConfigurationsIsMutable();
                this.configurations_.add(index, value);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, value);
            }
            return this;
        }

        public Builder addConfigurations(SignalChain.Builder builderForValue) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.add(builderForValue.m680build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builderForValue.m680build());
            }
            return this;
        }

        public Builder addConfigurations(int index, SignalChain.Builder builderForValue) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.add(index, builderForValue.m680build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, builderForValue.m680build());
            }
            return this;
        }

        public Builder addAllConfigurations(Iterable<? extends SignalChain> values) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                AbstractMessageLite.Builder.addAll(values, this.configurations_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(values);
            }
            return this;
        }

        public Builder clearConfigurations() {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.configurations_ = Collections.emptyList();
                this.bitField0_ &= -3;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeConfigurations(int index) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.remove(index);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(index);
            }
            return this;
        }

        public SignalChain.Builder getConfigurationsBuilder(int index) {
            return getConfigurationsFieldBuilder().getBuilder(index);
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public SignalChainOrBuilder getConfigurationsOrBuilder(int index) {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.configurations_.get(index);
            }
            return (SignalChainOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(index);
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public List<? extends SignalChainOrBuilder> getConfigurationsOrBuilderList() {
            RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.configurations_);
        }

        public SignalChain.Builder addConfigurationsBuilder() {
            return getConfigurationsFieldBuilder().addBuilder(SignalChain.getDefaultInstance());
        }

        public SignalChain.Builder addConfigurationsBuilder(int index) {
            return getConfigurationsFieldBuilder().addBuilder(index, SignalChain.getDefaultInstance());
        }

        public List<SignalChain.Builder> getConfigurationsBuilderList() {
            return getConfigurationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<SignalChain, SignalChain.Builder, SignalChainOrBuilder> getConfigurationsFieldBuilder() {
            if (this.configurationsBuilder_ == null) {
                this.configurationsBuilder_ = new RepeatedFieldBuilderV3<>(this.configurations_, (this.bitField0_ & 2) != 0, getParentForChildren(), isClean());
                this.configurations_ = null;
            }
            return this.configurationsBuilder_;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public String getCreator() {
            Object ref = this.creator_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.creator_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public ByteString getCreatorBytes() {
            Object ref = this.creator_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.creator_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setCreator(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.creator_ = value;
            onChanged();
            return this;
        }

        public Builder clearCreator() {
            this.creator_ = Archive.getDefaultInstance().getCreator();
            onChanged();
            return this;
        }

        public Builder setCreatorBytes(ByteString value) {
            if (value != null) {
                Archive.checkByteStringIsUtf8(value);
                this.creator_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public boolean hasCreatorVersion() {
            return (this.creatorVersionBuilder_ == null && this.creatorVersion_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public Version getCreatorVersion() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.creatorVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.creatorVersion_;
                return version == null ? Version.getDefaultInstance() : version;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setCreatorVersion(Version value) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.creatorVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.creatorVersion_ = value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(value);
            }
            return this;
        }

        public Builder setCreatorVersion(Version.Builder builderForValue) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.creatorVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.creatorVersion_ = builderForValue.m780build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builderForValue.m780build());
            }
            return this;
        }

        public Builder mergeCreatorVersion(Version value) {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.creatorVersionBuilder_;
            if (singleFieldBuilderV3 == null) {
                Version version = this.creatorVersion_;
                if (version != null) {
                    this.creatorVersion_ = Version.newBuilder(version).mergeFrom(value).m782buildPartial();
                } else {
                    this.creatorVersion_ = value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(value);
            }
            return this;
        }

        public Builder clearCreatorVersion() {
            if (this.creatorVersionBuilder_ == null) {
                this.creatorVersion_ = null;
                onChanged();
            } else {
                this.creatorVersion_ = null;
                this.creatorVersionBuilder_ = null;
            }
            return this;
        }

        public Version.Builder getCreatorVersionBuilder() {
            onChanged();
            return getCreatorVersionFieldBuilder().getBuilder();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public VersionOrBuilder getCreatorVersionOrBuilder() {
            SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> singleFieldBuilderV3 = this.creatorVersionBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (VersionOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Version version = this.creatorVersion_;
            if (version != null) {
                return version;
            }
            return Version.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Version, Version.Builder, VersionOrBuilder> getCreatorVersionFieldBuilder() {
            if (this.creatorVersionBuilder_ == null) {
                this.creatorVersionBuilder_ = new SingleFieldBuilderV3<>(getCreatorVersion(), getParentForChildren(), isClean());
                this.creatorVersion_ = null;
            }
            return this.creatorVersionBuilder_;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public String getAuthor() {
            Object ref = this.author_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.author_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public ByteString getAuthorBytes() {
            Object ref = this.author_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.author_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setAuthor(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.author_ = value;
            onChanged();
            return this;
        }

        public Builder clearAuthor() {
            this.author_ = Archive.getDefaultInstance().getAuthor();
            onChanged();
            return this;
        }

        public Builder setAuthorBytes(ByteString value) {
            if (value != null) {
                Archive.checkByteStringIsUtf8(value);
                this.author_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public String getProject() {
            Object ref = this.project_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.project_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public ByteString getProjectBytes() {
            Object ref = this.project_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.project_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setProject(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.project_ = value;
            onChanged();
            return this;
        }

        public Builder clearProject() {
            this.project_ = Archive.getDefaultInstance().getProject();
            onChanged();
            return this;
        }

        public Builder setProjectBytes(ByteString value) {
            if (value != null) {
                Archive.checkByteStringIsUtf8(value);
                this.project_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public String getManufacturer() {
            Object ref = this.manufacturer_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.manufacturer_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ArchiveOrBuilder
        public ByteString getManufacturerBytes() {
            Object ref = this.manufacturer_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.manufacturer_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setManufacturer(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.manufacturer_ = value;
            onChanged();
            return this;
        }

        public Builder clearManufacturer() {
            this.manufacturer_ = Archive.getDefaultInstance().getManufacturer();
            onChanged();
            return this;
        }

        public Builder setManufacturerBytes(ByteString value) {
            if (value != null) {
                Archive.checkByteStringIsUtf8(value);
                this.manufacturer_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m136setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m130mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static Archive getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Archive> parser() {
        return PARSER;
    }

    public Parser<Archive> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Archive m92getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}

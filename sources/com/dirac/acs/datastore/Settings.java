package com.dirac.acs.datastore;

import com.dirac.acs.datastore.Configuration;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Timestamp;
import com.google.protobuf.TimestampOrBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class Settings extends GeneratedMessageV3 implements SettingsOrBuilder {
    public static final int CONFIGURATIONS_FIELD_NUMBER = 3;
    public static final int CREATION_TIME_FIELD_NUMBER = 2;
    public static final int ISDIRACENABLED_FIELD_NUMBER = 5;
    public static final int ISENABLED_FIELD_NUMBER = 7;
    public static final int ISGEF_FIELD_NUMBER = 4;
    public static final int OUTPUTTYPE_FIELD_NUMBER = 6;
    public static final int UUID_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private List<Configuration> configurations_;
    private Timestamp creationTime_;
    private boolean isDiracEnabled_;
    private int isEnabledMemoizedSerializedSize;
    private Internal.BooleanList isEnabled_;
    private boolean isGEF_;
    private byte memoizedIsInitialized;
    private int outputTypeMemoizedSerializedSize;
    private Internal.IntList outputType_;
    private volatile Object uuid_;
    private static final Settings DEFAULT_INSTANCE = new Settings();
    private static final Parser<Settings> PARSER = new AbstractParser<Settings>() { // from class: com.dirac.acs.datastore.Settings.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Settings m621parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new Settings(input, extensionRegistry);
        }
    };

    static /* synthetic */ Internal.IntList access$100() {
        return emptyIntList();
    }

    static /* synthetic */ Internal.BooleanList access$1100() {
        return emptyBooleanList();
    }

    static /* synthetic */ Internal.BooleanList access$200() {
        return emptyBooleanList();
    }

    static /* synthetic */ Internal.IntList access$600() {
        return emptyIntList();
    }

    static /* synthetic */ Internal.IntList access$800() {
        return emptyIntList();
    }

    static /* synthetic */ Internal.BooleanList access$900() {
        return emptyBooleanList();
    }

    private Settings(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.outputTypeMemoizedSerializedSize = -1;
        this.isEnabledMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
    }

    private Settings() {
        this.outputTypeMemoizedSerializedSize = -1;
        this.isEnabledMemoizedSerializedSize = -1;
        this.memoizedIsInitialized = (byte) -1;
        this.uuid_ = "";
        this.configurations_ = Collections.emptyList();
        this.outputType_ = emptyIntList();
        this.isEnabled_ = emptyBooleanList();
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new Settings();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0011. Please report as an issue. */
    private Settings(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
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
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                            case 10:
                                String s = input.readStringRequireUtf8();
                                this.uuid_ = s;
                            case 18:
                                Timestamp timestamp = this.creationTime_;
                                Timestamp.Builder subBuilder = timestamp != null ? timestamp.toBuilder() : null;
                                Timestamp readMessage = input.readMessage(Timestamp.parser(), extensionRegistry);
                                this.creationTime_ = readMessage;
                                if (subBuilder != null) {
                                    subBuilder.mergeFrom(readMessage);
                                    this.creationTime_ = subBuilder.buildPartial();
                                }
                            case 26:
                                if ((mutable_bitField0_ & 1) == 0) {
                                    this.configurations_ = new ArrayList();
                                    mutable_bitField0_ |= 1;
                                }
                                this.configurations_.add((Configuration) input.readMessage(Configuration.parser(), extensionRegistry));
                            case 32:
                                this.isGEF_ = input.readBool();
                            case 40:
                                this.isDiracEnabled_ = input.readBool();
                            case 48:
                                int length = mutable_bitField0_ & 2;
                                if (length == 0) {
                                    this.outputType_ = newIntList();
                                    mutable_bitField0_ |= 2;
                                }
                                this.outputType_.addInt(input.readInt32());
                            case 50:
                                int length2 = input.readRawVarint32();
                                int limit = input.pushLimit(length2);
                                if ((mutable_bitField0_ & 2) == 0 && input.getBytesUntilLimit() > 0) {
                                    this.outputType_ = newIntList();
                                    mutable_bitField0_ |= 2;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.outputType_.addInt(input.readInt32());
                                }
                                input.popLimit(limit);
                                break;
                            case 56:
                                int length3 = mutable_bitField0_ & 4;
                                if (length3 == 0) {
                                    this.isEnabled_ = newBooleanList();
                                    mutable_bitField0_ |= 4;
                                }
                                this.isEnabled_.addBoolean(input.readBool());
                            case 58:
                                int length4 = input.readRawVarint32();
                                int limit2 = input.pushLimit(length4);
                                if ((mutable_bitField0_ & 4) == 0 && input.getBytesUntilLimit() > 0) {
                                    this.isEnabled_ = newBooleanList();
                                    mutable_bitField0_ |= 4;
                                }
                                while (input.getBytesUntilLimit() > 0) {
                                    this.isEnabled_.addBoolean(input.readBool());
                                }
                                input.popLimit(limit2);
                                break;
                            default:
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                        }
                    } catch (IOException e) {
                        throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                    }
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if ((mutable_bitField0_ & 1) != 0) {
                    this.configurations_ = Collections.unmodifiableList(this.configurations_);
                }
                if ((mutable_bitField0_ & 2) != 0) {
                    this.outputType_.makeImmutable();
                }
                if ((mutable_bitField0_ & 4) != 0) {
                    this.isEnabled_.makeImmutable();
                }
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Settings_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Settings_fieldAccessorTable.ensureFieldAccessorsInitialized(Settings.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
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

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public ByteString getUuidBytes() {
        Object ref = this.uuid_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.uuid_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public boolean hasCreationTime() {
        return this.creationTime_ != null;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public Timestamp getCreationTime() {
        Timestamp timestamp = this.creationTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public TimestampOrBuilder getCreationTimeOrBuilder() {
        return getCreationTime();
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public List<Configuration> getConfigurationsList() {
        return this.configurations_;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public List<? extends ConfigurationOrBuilder> getConfigurationsOrBuilderList() {
        return this.configurations_;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public int getConfigurationsCount() {
        return this.configurations_.size();
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public Configuration getConfigurations(int index) {
        return this.configurations_.get(index);
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public ConfigurationOrBuilder getConfigurationsOrBuilder(int index) {
        return this.configurations_.get(index);
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public boolean getIsGEF() {
        return this.isGEF_;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public boolean getIsDiracEnabled() {
        return this.isDiracEnabled_;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public List<Integer> getOutputTypeList() {
        return this.outputType_;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public int getOutputTypeCount() {
        return this.outputType_.size();
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public int getOutputType(int index) {
        return this.outputType_.getInt(index);
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public List<Boolean> getIsEnabledList() {
        return this.isEnabled_;
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public int getIsEnabledCount() {
        return this.isEnabled_.size();
    }

    @Override // com.dirac.acs.datastore.SettingsOrBuilder
    public boolean getIsEnabled(int index) {
        return this.isEnabled_.getBoolean(index);
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
        getSerializedSize();
        if (!getUuidBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.uuid_);
        }
        if (this.creationTime_ != null) {
            output.writeMessage(2, getCreationTime());
        }
        for (int i = 0; i < this.configurations_.size(); i++) {
            output.writeMessage(3, this.configurations_.get(i));
        }
        boolean z = this.isGEF_;
        if (z) {
            output.writeBool(4, z);
        }
        boolean z2 = this.isDiracEnabled_;
        if (z2) {
            output.writeBool(5, z2);
        }
        if (getOutputTypeList().size() > 0) {
            output.writeUInt32NoTag(50);
            output.writeUInt32NoTag(this.outputTypeMemoizedSerializedSize);
        }
        for (int i2 = 0; i2 < this.outputType_.size(); i2++) {
            output.writeInt32NoTag(this.outputType_.getInt(i2));
        }
        if (getIsEnabledList().size() > 0) {
            output.writeUInt32NoTag(58);
            output.writeUInt32NoTag(this.isEnabledMemoizedSerializedSize);
        }
        for (int i3 = 0; i3 < this.isEnabled_.size(); i3++) {
            output.writeBoolNoTag(this.isEnabled_.getBoolean(i3));
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = getUuidBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.uuid_);
        if (this.creationTime_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, getCreationTime());
        }
        for (int i = 0; i < this.configurations_.size(); i++) {
            size2 += CodedOutputStream.computeMessageSize(3, this.configurations_.get(i));
        }
        boolean z = this.isGEF_;
        if (z) {
            size2 += CodedOutputStream.computeBoolSize(4, z);
        }
        boolean z2 = this.isDiracEnabled_;
        if (z2) {
            size2 += CodedOutputStream.computeBoolSize(5, z2);
        }
        int dataSize = 0;
        for (int i2 = 0; i2 < this.outputType_.size(); i2++) {
            dataSize += CodedOutputStream.computeInt32SizeNoTag(this.outputType_.getInt(i2));
        }
        int size3 = size2 + dataSize;
        if (!getOutputTypeList().isEmpty()) {
            size3 = size3 + 1 + CodedOutputStream.computeInt32SizeNoTag(dataSize);
        }
        this.outputTypeMemoizedSerializedSize = dataSize;
        int dataSize2 = getIsEnabledList().size() * 1;
        int size4 = size3 + dataSize2;
        if (!getIsEnabledList().isEmpty()) {
            size4 = size4 + 1 + CodedOutputStream.computeInt32SizeNoTag(dataSize2);
        }
        this.isEnabledMemoizedSerializedSize = dataSize2;
        int size5 = size4 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size5;
        return size5;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Settings)) {
            return super.equals(obj);
        }
        Settings other = (Settings) obj;
        if (getUuid().equals(other.getUuid()) && hasCreationTime() == other.hasCreationTime()) {
            return (!hasCreationTime() || getCreationTime().equals(other.getCreationTime())) && getConfigurationsList().equals(other.getConfigurationsList()) && getIsGEF() == other.getIsGEF() && getIsDiracEnabled() == other.getIsDiracEnabled() && getOutputTypeList().equals(other.getOutputTypeList()) && getIsEnabledList().equals(other.getIsEnabledList()) && this.unknownFields.equals(other.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (((((41 * 19) + getDescriptor().hashCode()) * 37) + 1) * 53) + getUuid().hashCode();
        if (hasCreationTime()) {
            hash = (((hash * 37) + 2) * 53) + getCreationTime().hashCode();
        }
        if (getConfigurationsCount() > 0) {
            hash = (((hash * 37) + 3) * 53) + getConfigurationsList().hashCode();
        }
        int hash2 = (((((((hash * 37) + 4) * 53) + Internal.hashBoolean(getIsGEF())) * 37) + 5) * 53) + Internal.hashBoolean(getIsDiracEnabled());
        if (getOutputTypeCount() > 0) {
            hash2 = (((hash2 * 37) + 6) * 53) + getOutputTypeList().hashCode();
        }
        if (getIsEnabledCount() > 0) {
            hash2 = (((hash2 * 37) + 7) * 53) + getIsEnabledList().hashCode();
        }
        int hash3 = (hash2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash3;
        return hash3;
    }

    public static Settings parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (Settings) PARSER.parseFrom(data);
    }

    public static Settings parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Settings) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Settings parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (Settings) PARSER.parseFrom(data);
    }

    public static Settings parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Settings) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Settings parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (Settings) PARSER.parseFrom(data);
    }

    public static Settings parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (Settings) PARSER.parseFrom(data, extensionRegistry);
    }

    public static Settings parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Settings parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Settings parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static Settings parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static Settings parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static Settings parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m618newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m620toBuilder();
    }

    public static Builder newBuilder(Settings prototype) {
        return DEFAULT_INSTANCE.m620toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m620toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m617newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SettingsOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> configurationsBuilder_;
        private List<Configuration> configurations_;
        private SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> creationTimeBuilder_;
        private Timestamp creationTime_;
        private boolean isDiracEnabled_;
        private Internal.BooleanList isEnabled_;
        private boolean isGEF_;
        private Internal.IntList outputType_;
        private Object uuid_;

        public static final Descriptors.Descriptor getDescriptor() {
            return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Settings_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Settings_fieldAccessorTable.ensureFieldAccessorsInitialized(Settings.class, Builder.class);
        }

        private Builder() {
            this.uuid_ = "";
            this.configurations_ = Collections.emptyList();
            this.outputType_ = Settings.access$600();
            this.isEnabled_ = Settings.access$900();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.uuid_ = "";
            this.configurations_ = Collections.emptyList();
            this.outputType_ = Settings.access$600();
            this.isEnabled_ = Settings.access$900();
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (Settings.alwaysUseFieldBuilders) {
                getConfigurationsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m631clear() {
            super.clear();
            this.uuid_ = "";
            if (this.creationTimeBuilder_ == null) {
                this.creationTime_ = null;
            } else {
                this.creationTime_ = null;
                this.creationTimeBuilder_ = null;
            }
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.configurations_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.isGEF_ = false;
            this.isDiracEnabled_ = false;
            this.outputType_ = Settings.access$100();
            this.bitField0_ &= -3;
            this.isEnabled_ = Settings.access$200();
            this.bitField0_ &= -5;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return SettingsOuterClass.internal_static_com_dirac_acs_datastore_Settings_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Settings m644getDefaultInstanceForType() {
            return Settings.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Settings m625build() {
            Settings result = m627buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Settings m627buildPartial() {
            Settings result = new Settings(this);
            int i = this.bitField0_;
            result.uuid_ = this.uuid_;
            SingleFieldBuilderV3<Timestamp, Timestamp.Builder, TimestampOrBuilder> singleFieldBuilderV3 = this.creationTimeBuilder_;
            if (singleFieldBuilderV3 == null) {
                result.creationTime_ = this.creationTime_;
            } else {
                result.creationTime_ = singleFieldBuilderV3.build();
            }
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.configurations_ = Collections.unmodifiableList(this.configurations_);
                    this.bitField0_ &= -2;
                }
                result.configurations_ = this.configurations_;
            } else {
                result.configurations_ = repeatedFieldBuilderV3.build();
            }
            result.isGEF_ = this.isGEF_;
            result.isDiracEnabled_ = this.isDiracEnabled_;
            if ((this.bitField0_ & 2) != 0) {
                this.outputType_.makeImmutable();
                this.bitField0_ &= -3;
            }
            result.outputType_ = this.outputType_;
            if ((this.bitField0_ & 4) != 0) {
                this.isEnabled_.makeImmutable();
                this.bitField0_ &= -5;
            }
            result.isEnabled_ = this.isEnabled_;
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m642clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m655setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m633clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m636clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m657setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m623addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m649mergeFrom(Message other) {
            if (other instanceof Settings) {
                return mergeFrom((Settings) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(Settings other) {
            if (other == Settings.getDefaultInstance()) {
                return this;
            }
            if (!other.getUuid().isEmpty()) {
                this.uuid_ = other.uuid_;
                onChanged();
            }
            if (other.hasCreationTime()) {
                mergeCreationTime(other.getCreationTime());
            }
            if (this.configurationsBuilder_ == null) {
                if (!other.configurations_.isEmpty()) {
                    if (this.configurations_.isEmpty()) {
                        this.configurations_ = other.configurations_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureConfigurationsIsMutable();
                        this.configurations_.addAll(other.configurations_);
                    }
                    onChanged();
                }
            } else if (!other.configurations_.isEmpty()) {
                if (this.configurationsBuilder_.isEmpty()) {
                    this.configurationsBuilder_.dispose();
                    RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = null;
                    this.configurationsBuilder_ = null;
                    this.configurations_ = other.configurations_;
                    this.bitField0_ &= -2;
                    if (Settings.alwaysUseFieldBuilders) {
                        repeatedFieldBuilderV3 = getConfigurationsFieldBuilder();
                    }
                    this.configurationsBuilder_ = repeatedFieldBuilderV3;
                } else {
                    this.configurationsBuilder_.addAllMessages(other.configurations_);
                }
            }
            if (other.getIsGEF()) {
                setIsGEF(other.getIsGEF());
            }
            if (other.getIsDiracEnabled()) {
                setIsDiracEnabled(other.getIsDiracEnabled());
            }
            if (!other.outputType_.isEmpty()) {
                if (this.outputType_.isEmpty()) {
                    this.outputType_ = other.outputType_;
                    this.bitField0_ &= -3;
                } else {
                    ensureOutputTypeIsMutable();
                    this.outputType_.addAll(other.outputType_);
                }
                onChanged();
            }
            if (!other.isEnabled_.isEmpty()) {
                if (this.isEnabled_.isEmpty()) {
                    this.isEnabled_ = other.isEnabled_;
                    this.bitField0_ &= -5;
                } else {
                    ensureIsEnabledIsMutable();
                    this.isEnabled_.addAll(other.isEnabled_);
                }
                onChanged();
            }
            m653mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m650mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            Settings parsedMessage = null;
            try {
                try {
                    parsedMessage = (Settings) Settings.PARSER.parsePartialFrom(input, extensionRegistry);
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

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
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

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
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
            this.uuid_ = Settings.getDefaultInstance().getUuid();
            onChanged();
            return this;
        }

        public Builder setUuidBytes(ByteString value) {
            if (value != null) {
                Settings.checkByteStringIsUtf8(value);
                this.uuid_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public boolean hasCreationTime() {
            return (this.creationTimeBuilder_ == null && this.creationTime_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
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

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
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

        private void ensureConfigurationsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.configurations_ = new ArrayList(this.configurations_);
                this.bitField0_ |= 1;
            }
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public List<Configuration> getConfigurationsList() {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.configurations_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public int getConfigurationsCount() {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.configurations_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public Configuration getConfigurations(int index) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.configurations_.get(index);
            }
            return repeatedFieldBuilderV3.getMessage(index);
        }

        public Builder setConfigurations(int index, Configuration value) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
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

        public Builder setConfigurations(int index, Configuration.Builder builderForValue) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.set(index, builderForValue.m159build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(index, builderForValue.m159build());
            }
            return this;
        }

        public Builder addConfigurations(Configuration value) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
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

        public Builder addConfigurations(int index, Configuration value) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
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

        public Builder addConfigurations(Configuration.Builder builderForValue) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.add(builderForValue.m159build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builderForValue.m159build());
            }
            return this;
        }

        public Builder addConfigurations(int index, Configuration.Builder builderForValue) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.add(index, builderForValue.m159build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(index, builderForValue.m159build());
            }
            return this;
        }

        public Builder addAllConfigurations(Iterable<? extends Configuration> values) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
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
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.configurations_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeConfigurations(int index) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureConfigurationsIsMutable();
                this.configurations_.remove(index);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(index);
            }
            return this;
        }

        public Configuration.Builder getConfigurationsBuilder(int index) {
            return getConfigurationsFieldBuilder().getBuilder(index);
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public ConfigurationOrBuilder getConfigurationsOrBuilder(int index) {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.configurations_.get(index);
            }
            return (ConfigurationOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(index);
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public List<? extends ConfigurationOrBuilder> getConfigurationsOrBuilderList() {
            RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> repeatedFieldBuilderV3 = this.configurationsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.configurations_);
        }

        public Configuration.Builder addConfigurationsBuilder() {
            return getConfigurationsFieldBuilder().addBuilder(Configuration.getDefaultInstance());
        }

        public Configuration.Builder addConfigurationsBuilder(int index) {
            return getConfigurationsFieldBuilder().addBuilder(index, Configuration.getDefaultInstance());
        }

        public List<Configuration.Builder> getConfigurationsBuilderList() {
            return getConfigurationsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<Configuration, Configuration.Builder, ConfigurationOrBuilder> getConfigurationsFieldBuilder() {
            if (this.configurationsBuilder_ == null) {
                this.configurationsBuilder_ = new RepeatedFieldBuilderV3<>(this.configurations_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.configurations_ = null;
            }
            return this.configurationsBuilder_;
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public boolean getIsGEF() {
            return this.isGEF_;
        }

        public Builder setIsGEF(boolean value) {
            this.isGEF_ = value;
            onChanged();
            return this;
        }

        public Builder clearIsGEF() {
            this.isGEF_ = false;
            onChanged();
            return this;
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public boolean getIsDiracEnabled() {
            return this.isDiracEnabled_;
        }

        public Builder setIsDiracEnabled(boolean value) {
            this.isDiracEnabled_ = value;
            onChanged();
            return this;
        }

        public Builder clearIsDiracEnabled() {
            this.isDiracEnabled_ = false;
            onChanged();
            return this;
        }

        private void ensureOutputTypeIsMutable() {
            if ((this.bitField0_ & 2) == 0) {
                this.outputType_ = Settings.mutableCopy(this.outputType_);
                this.bitField0_ |= 2;
            }
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public List<Integer> getOutputTypeList() {
            return (this.bitField0_ & 2) != 0 ? Collections.unmodifiableList(this.outputType_) : this.outputType_;
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public int getOutputTypeCount() {
            return this.outputType_.size();
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public int getOutputType(int index) {
            return this.outputType_.getInt(index);
        }

        public Builder setOutputType(int index, int value) {
            ensureOutputTypeIsMutable();
            this.outputType_.setInt(index, value);
            onChanged();
            return this;
        }

        public Builder addOutputType(int value) {
            ensureOutputTypeIsMutable();
            this.outputType_.addInt(value);
            onChanged();
            return this;
        }

        public Builder addAllOutputType(Iterable<? extends Integer> values) {
            ensureOutputTypeIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.outputType_);
            onChanged();
            return this;
        }

        public Builder clearOutputType() {
            this.outputType_ = Settings.access$800();
            this.bitField0_ &= -3;
            onChanged();
            return this;
        }

        private void ensureIsEnabledIsMutable() {
            if ((this.bitField0_ & 4) == 0) {
                this.isEnabled_ = Settings.mutableCopy(this.isEnabled_);
                this.bitField0_ |= 4;
            }
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public List<Boolean> getIsEnabledList() {
            return (this.bitField0_ & 4) != 0 ? Collections.unmodifiableList(this.isEnabled_) : this.isEnabled_;
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public int getIsEnabledCount() {
            return this.isEnabled_.size();
        }

        @Override // com.dirac.acs.datastore.SettingsOrBuilder
        public boolean getIsEnabled(int index) {
            return this.isEnabled_.getBoolean(index);
        }

        public Builder setIsEnabled(int index, boolean value) {
            ensureIsEnabledIsMutable();
            this.isEnabled_.setBoolean(index, value);
            onChanged();
            return this;
        }

        public Builder addIsEnabled(boolean value) {
            ensureIsEnabledIsMutable();
            this.isEnabled_.addBoolean(value);
            onChanged();
            return this;
        }

        public Builder addAllIsEnabled(Iterable<? extends Boolean> values) {
            ensureIsEnabledIsMutable();
            AbstractMessageLite.Builder.addAll(values, this.isEnabled_);
            onChanged();
            return this;
        }

        public Builder clearIsEnabled() {
            this.isEnabled_ = Settings.access$1100();
            this.bitField0_ &= -5;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m659setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m653mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static Settings getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Settings> parser() {
        return PARSER;
    }

    public Parser<Settings> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Settings m615getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}

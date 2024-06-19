CREATE TYPE alertlevel AS ENUM ('NORMAL', 'HIGH', 'CRITICAL');
CREATE TYPE public.assetstate AS ENUM ('WORKING', 'IDLE');
CREATE TYPE public.downtimetype AS ENUM ('MANUAL', 'AUTOMATIC');

-- Create Organization table first
CREATE TABLE Organization (
    id UUID PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Role (
    id UUID PRIMARY KEY,
    role VARCHAR(255),
    color VARCHAR(255),
    organization_id UUID,
    FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

CREATE TABLE Asset (
    id UUID PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    asset_key VARCHAR(255) UNIQUE,
    last_communication TIMESTAMPTZ NOT NULL,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    organization_id UUID,
    FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

CREATE TABLE Vibration_Sensor_Reading (
    id UUID PRIMARY KEY,
    data_array DOUBLE PRECISION[],
    asset_key VARCHAR(255) UNIQUE,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    asset_id UUID,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

CREATE TABLE FFT (
    id UUID PRIMARY KEY,
    asset_key VARCHAR(255) UNIQUE,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    asset_id UUID,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

CREATE TABLE Alert (
    id UUID PRIMARY KEY,
    alert_value DOUBLE PRECISION,
    alert_critical_value DOUBLE PRECISION,
    alert_level alertlevel,
    alert_tags VARCHAR(255),
    id_asset UUID NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES Asset(id)
);

CREATE TABLE Bearings (
    id UUID PRIMARY KEY,
    brand VARCHAR(255),
    identifier VARCHAR(255),
    element_amount INT,
    FTF DOUBLE PRECISION,
    BSF DOUBLE PRECISION,
    BPFO DOUBLE PRECISION,
    BPFI DOUBLE PRECISION
);

CREATE TABLE Data (
    id UUID PRIMARY KEY,
    acceleration_rms_x DOUBLE PRECISION,
    acceleration_rms_y DOUBLE PRECISION,
    acceleration_rms_z DOUBLE PRECISION,
    speed_rms_x DOUBLE PRECISION,
    speed_rms_y DOUBLE PRECISION,
    speed_rms_z DOUBLE PRECISION,
    temperature DOUBLE PRECISION,
    battery DOUBLE PRECISION,
    asset_key VARCHAR(255) UNIQUE,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    asset_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

CREATE TABLE Distribution (
    id UUID PRIMARY KEY,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    vibration_sensor_reading_id UUID,
    FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id)
);

CREATE TABLE Downtime (
    id UUID PRIMARY KEY,
    downtime DOUBLE PRECISION,
    downtime_type downtimetype,
    asset_state assetstate,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    asset_id UUID,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

CREATE TABLE Employees (
    id UUID PRIMARY KEY,
    name VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    salt VARCHAR(255),
    image_link VARCHAR(255),
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    role_id UUID,
    organization_id UUID,
    FOREIGN KEY (role_id) REFERENCES Role(id),
    FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

CREATE TABLE Envelope (
    id UUID PRIMARY KEY,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    fft_id UUID,
    FOREIGN KEY (fft_id) REFERENCES FFT(id)
);

CREATE TABLE Events (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ NOT NULL,
    description VARCHAR(255),
    alert_level alertlevel,
    asset_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

CREATE TABLE FFT_Statistical_Values (
    id UUID PRIMARY KEY,
    rpm DOUBLE PRECISION,
    rms DOUBLE PRECISION,
    peak_to_peak DOUBLE PRECISION,
    skewness DOUBLE PRECISION,
    kurtosis DOUBLE PRECISION,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    fft_id UUID,
    FOREIGN KEY (fft_id) REFERENCES FFT(id)
);

CREATE TABLE MachineIntervals (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ NOT NULL,
    asset_state assetstate,
    id_asset UUID NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES Asset(id)
);

CREATE TABLE PasswordResetToken (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255),
    user_id UUID NOT NULL,
    expiry_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Employees(id)
);

CREATE TABLE Privilege (
    id UUID PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Vibration_Sensor_Reading_Statistical_Values (
    id UUID PRIMARY KEY,
    crest_factor DOUBLE PRECISION,
    k_factor DOUBLE PRECISION,
    z_score DOUBLE PRECISION,
    rms DOUBLE PRECISION,
    peak DOUBLE PRECISION,
    peak_to_peak DOUBLE PRECISION,
    standard_deviation DOUBLE PRECISION,
    mean DOUBLE PRECISION,
    skewness DOUBLE PRECISION,
    kurtosis DOUBLE PRECISION,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    vibration_sensor_reading_id UUID,
    FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id)
);

CREATE TABLE Workorder (
    id UUID PRIMARY KEY,
    added TIMESTAMP WITH TIME ZONE DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo'),
    organization_id UUID,
    FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

CREATE TABLE Vector (
    id UUID PRIMARY KEY,
    x DOUBLE PRECISION,
    y DOUBLE PRECISION
);

CREATE TABLE roles_privileges (
    id_role UUID NOT NULL,
    id_privilege UUID NOT NULL,
    PRIMARY KEY (id_role, id_privilege),
    FOREIGN KEY (id_role) REFERENCES Role(id),
    FOREIGN KEY (id_privilege) REFERENCES Privilege(id)
);

-- one-to-many associations
ALTER TABLE Asset ADD CONSTRAINT fk_organization FOREIGN KEY (organization_id) REFERENCES Organization(id);
ALTER TABLE Alert ADD CONSTRAINT fk_asset FOREIGN KEY (id_asset) REFERENCES Asset(id);
ALTER TABLE Data ADD CONSTRAINT fk_asset_data FOREIGN KEY (asset_id) REFERENCES Asset(id);
ALTER TABLE Events ADD CONSTRAINT fk_asset_events FOREIGN KEY (asset_id) REFERENCES Asset(id);
ALTER TABLE MachineIntervals ADD CONSTRAINT fk_asset_machineintervals FOREIGN KEY (id_asset) REFERENCES Asset(id);
ALTER TABLE Vibration_Sensor_Reading ADD CONSTRAINT fk_asset_vibration FOREIGN KEY (asset_id) REFERENCES Asset(id);

-- one-to-one associations
ALTER TABLE Distribution ADD CONSTRAINT fk_vibration_sensor_reading FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id);
ALTER TABLE Downtime ADD CONSTRAINT fk_asset_downtime FOREIGN KEY (asset_id) REFERENCES Asset(id);
ALTER TABLE Envelope ADD CONSTRAINT fk_fft FOREIGN KEY (fft_id) REFERENCES FFT(id);
ALTER TABLE FFT_Statistical_Values ADD CONSTRAINT fk_fft_statistical FOREIGN KEY (fft_id) REFERENCES FFT(id);
ALTER TABLE Vibration_Sensor_Reading_Statistical_Values ADD CONSTRAINT fk_vibration_sensor_reading_statistical FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id);
ALTER TABLE Workorder ADD CONSTRAINT fk_organization_workorder FOREIGN KEY (organization_id) REFERENCES Organization(id);

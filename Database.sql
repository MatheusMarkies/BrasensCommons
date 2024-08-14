
-- Organization Table
CREATE TABLE Organization (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Role Table
CREATE TABLE Role (
    id UUID PRIMARY KEY,
    role VARCHAR(255) NOT NULL,
    color VARCHAR(255),
    organization_id UUID NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES Organization(id),
    UNIQUE (role, organization_id)  -- Ensure role uniqueness within an organization
);

-- Bearings Table
CREATE TABLE Bearings (
    id UUID PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    identifier VARCHAR(255) NOT NULL,
    element_amount INT NOT NULL,
    FTF DOUBLE PRECISION,
    BSF DOUBLE PRECISION,
    BPFO DOUBLE PRECISION,
    BPFI DOUBLE PRECISION
);

-- Asset Table
CREATE TABLE IF NOT EXISTS Asset (
    id UUID PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    asset_key VARCHAR(255) UNIQUE NOT NULL,
    rpm DOUBLE PRECISION NOT NULL,
    last_communication TIMESTAMPTZ NOT NULL,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    bearing_id UUID,
    organization_id UUID NOT NULL,
    asset_tree_id UUID,  -- Assuming this column already exists
    location_id UUID,  -- Assuming this column already exists
    FOREIGN KEY (organization_id) REFERENCES Organization(id),
    FOREIGN KEY (bearing_id) REFERENCES Bearings(id),
    FOREIGN KEY (location_id) REFERENCES Location_Tree(id)
);

-- AssetTree Table
CREATE TABLE IF NOT EXISTS Asset_Tree (
    id UUID PRIMARY KEY,
	asset_id UUID UNIQUE NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

-- LocationTree Table
CREATE TABLE IF NOT EXISTS Location_Tree (
    id UUID PRIMARY KEY,
    location VARCHAR(255) UNIQUE NOT NULL
);

-- Adding foreign key constraint if not already existing
ALTER TABLE IF EXISTS Asset ADD CONSTRAINT asset_id FOREIGN KEY (asset_tree_id)
REFERENCES Asset_Tree (id);

-- History Table
CREATE TABLE History (
    id UUID PRIMARY KEY,
    asset_key VARCHAR(255) UNIQUE NOT NULL,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    asset_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

-- Vibration_Sensor_Reading Table
CREATE TABLE Vibration_Sensor_Reading (
    id UUID PRIMARY KEY,
    data_array DOUBLE PRECISION[] NOT NULL,
    asset_key VARCHAR(255) UNIQUE NOT NULL,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    asset_id UUID NOT NULL,
    history_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id),
    FOREIGN KEY (history_id) REFERENCES History(id)
);

-- FFT Table
CREATE TABLE FFT (
    id UUID PRIMARY KEY,
    asset_key VARCHAR(255) UNIQUE NOT NULL,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    asset_id UUID NOT NULL,
    history_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id),
    FOREIGN KEY (history_id) REFERENCES History(id)
);

-- PSD Table
CREATE TABLE PSD (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    fft_id UUID NOT NULL,
    FOREIGN KEY (fft_id) REFERENCES FFT(id)
);

-- Alert Table
CREATE TABLE Alert (
    id UUID PRIMARY KEY,
    alert_value DOUBLE PRECISION NOT NULL,
    alert_critical_value DOUBLE PRECISION NOT NULL,
    alert_level alertlevel NOT NULL,
    alert_tags VARCHAR(255),
    id_asset UUID NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES Asset(id)
);

-- Data Table
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
    asset_key VARCHAR(255) UNIQUE NOT NULL,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    asset_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

-- Distribution Table
CREATE TABLE Distribution (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    vibration_sensor_reading_id UUID NOT NULL,
    FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id)
);

-- Downtime Table
CREATE TABLE Downtime (
    id UUID PRIMARY KEY,
    downtime DOUBLE PRECISION NOT NULL,
    downtime_type downtimetype NOT NULL,
    asset_state assetstate NOT NULL,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    asset_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

-- Employees Table
CREATE TABLE Employees (
    id UUID PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    image_link VARCHAR(255),
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    role_id UUID NOT NULL,
    organization_id UUID NOT NULL,
    FOREIGN KEY (role_id) REFERENCES Role(id),
    FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

-- Envelope Table
CREATE TABLE Envelope (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    fft_id UUID NOT NULL,
    FOREIGN KEY (fft_id) REFERENCES FFT(id)
);

-- Events Table
CREATE TABLE Events (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ NOT NULL,
    description VARCHAR(255),
    alert_level alertlevel NOT NULL,
    asset_id UUID NOT NULL,
    FOREIGN KEY (asset_id) REFERENCES Asset(id)
);

-- FFT_Statistical_Values Table
CREATE TABLE FFT_Statistical_Values (
    id UUID PRIMARY KEY,
    rpm DOUBLE PRECISION NOT NULL,
    rms DOUBLE PRECISION NOT NULL,
    peak_to_peak DOUBLE PRECISION,
    standard_deviation DOUBLE PRECISION,
    mean DOUBLE PRECISION,
    mode DOUBLE PRECISION,
    median DOUBLE PRECISION,
    variance DOUBLE PRECISION,
    skewness DOUBLE PRECISION,
    kurtosis DOUBLE PRECISION,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    fft_id UUID NOT NULL,
    FOREIGN KEY (fft_id) REFERENCES FFT(id)
);

-- MachineIntervals Table
CREATE TABLE Machine_Intervals (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ NOT NULL,
    asset_state assetstate NOT NULL,
    id_asset UUID NOT NULL,
    FOREIGN KEY (id_asset) REFERENCES Asset(id)
);

-- PasswordResetToken Table
CREATE TABLE Password_Reset_Token (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255) NOT NULL,
    user_id UUID NOT NULL,
    expiry_date TIMESTAMPTZ NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Employees(id)
);

-- Privilege Table
CREATE TABLE Privilege (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Vibration_Sensor_Reading_Statistical_Values Table
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
    mode DOUBLE PRECISION,
    median DOUBLE PRECISION,
    variance DOUBLE PRECISION,
    skewness DOUBLE PRECISION,
    kurtosis DOUBLE PRECISION,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    vibration_sensor_reading_id UUID NOT NULL,
    FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id)
);

-- Workorder Table
CREATE TABLE Workorder (
    id UUID PRIMARY KEY,
    added TIMESTAMPTZ DEFAULT (now() AT TIME ZONE 'America/Sao_Paulo') NOT NULL,
    organization_id UUID NOT NULL,
    FOREIGN KEY (organization_id) REFERENCES Organization(id)
);

-- Vector Table
CREATE TABLE Vector (
    id UUID PRIMARY KEY,
    x DOUBLE PRECISION NOT NULL,
    y DOUBLE PRECISION NOT NULL
);

-- Roles and Privileges Junction Table
CREATE TABLE roles_privileges (
    id_role UUID NOT NULL,
    id_privilege UUID NOT NULL,
    PRIMARY KEY (id_role, id_privilege),
    FOREIGN KEY (id_role) REFERENCES Roles(id),
    FOREIGN KEY (id_privilege) REFERENCES Privileges(id)
);

-- Ensure data integrity through constraints
ALTER TABLE Asset ADD CONSTRAINT fk_organization FOREIGN KEY (organization_id) REFERENCES Organization(id);
ALTER TABLE Alert ADD CONSTRAINT fk_asset FOREIGN KEY (id_asset) REFERENCES Asset(id);
ALTER TABLE Data ADD CONSTRAINT fk_asset_data FOREIGN KEY (asset_id) REFERENCES Asset(id);
ALTER TABLE Events ADD CONSTRAINT fk_asset_events FOREIGN KEY (asset_id) REFERENCES Asset(id);
ALTER TABLE Machine_Intervals ADD CONSTRAINT fk_asset_machineintervals FOREIGN KEY (id_asset) REFERENCES Asset(id);
ALTER TABLE Vibration_Sensor_Reading ADD CONSTRAINT fk_asset_vibration FOREIGN KEY (asset_id) REFERENCES Asset(id);
ALTER TABLE Location_Tree ADD CONSTRAINT fk_organization FOREIGN KEY (organization_id) REFERENCES Organization(id);

ALTER TABLE Asset ADD CONSTRAINT fk_location FOREIGN KEY (location_id) REFERENCES Location_Tree(id);

ALTER TABLE Distribution ADD CONSTRAINT fk_vibration_sensor_reading FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id);
ALTER TABLE Downtime ADD CONSTRAINT fk_asset_downtime FOREIGN KEY (asset_id) REFERENCES Asset(id);
ALTER TABLE Envelope ADD CONSTRAINT fk_fft FOREIGN KEY (fft_id) REFERENCES FFT(id);
ALTER TABLE FFT_Statistical_Values ADD CONSTRAINT fk_fft_statistical FOREIGN KEY (fft_id) REFERENCES FFT(id);
ALTER TABLE Vibration_Sensor_Reading_Statistical_Values ADD CONSTRAINT fk_vibration_sensor_reading_statistical FOREIGN KEY (vibration_sensor_reading_id) REFERENCES Vibration_Sensor_Reading(id);
ALTER TABLE Workorder ADD CONSTRAINT fk_organization_workorder FOREIGN KEY (organization_id) REFERENCES Organization(id);

ALTER TABLE FFT ADD CONSTRAINT fk_history_fft FOREIGN KEY (history_id) REFERENCES History(id);
ALTER TABLE Vibration_Sensor_Reading ADD CONSTRAINT fk_history_vibration_sensor_reading FOREIGN KEY (history_id) REFERENCES History(id);
ALTER TABLE PSD ADD CONSTRAINT fk_fft_psd FOREIGN KEY (fft_id) REFERENCES FFT(id);

ALTER TABLE Asset ADD CONSTRAINT fk_asset_tree FOREIGN KEY (asset_tree_id) REFERENCES Asset_Tree(id);

-- Add indexes to improve performance
CREATE INDEX idx_asset_name ON Asset(name);
CREATE INDEX idx_asset_key ON Asset(asset_key);
CREATE INDEX idx_data_asset_key ON Data(asset_key);
CREATE INDEX idx_event_asset_id ON Events(asset_id);
CREATE INDEX idx_vibration_asset_id ON Vibration_Sensor_Reading(asset_id);
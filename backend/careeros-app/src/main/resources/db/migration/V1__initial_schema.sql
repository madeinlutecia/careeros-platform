-- CareerOS Platform - Initial Schema (MVP)

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    provider VARCHAR(50) NOT NULL,
    provider_id VARCHAR(255),
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS master_profiles (
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    user_id UUID NOT NULL UNIQUE,
    full_name VARCHAR(255) NOT NULL,
    headline VARCHAR(500),
    current_position VARCHAR(255),
    about TEXT,
    github_url VARCHAR(500),
    linkedin_url VARCHAR(500),
    portfolio_url VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS profile_skills (
    profile_id UUID NOT NULL REFERENCES master_profiles(id) ON DELETE CASCADE,
    skill VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS profile_languages (
    profile_id UUID NOT NULL REFERENCES master_profiles(id) ON DELETE CASCADE,
    language VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS profile_experiences (
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    profile_id UUID NOT NULL REFERENCES master_profiles(id) ON DELETE CASCADE,
    company VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    start_date DATE,
    end_date DATE,
    current BOOLEAN DEFAULT FALSE,
    description TEXT
);

CREATE TABLE IF NOT EXISTS experience_technologies (
    experience_id UUID NOT NULL REFERENCES profile_experiences(id) ON DELETE CASCADE,
    technology VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS profile_educations (
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    profile_id UUID NOT NULL REFERENCES master_profiles(id) ON DELETE CASCADE,
    institution VARCHAR(255) NOT NULL,
    degree VARCHAR(255) NOT NULL,
    field_of_study VARCHAR(255),
    start_date DATE,
    end_date DATE
);

CREATE TABLE IF NOT EXISTS profile_certifications (
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    profile_id UUID NOT NULL REFERENCES master_profiles(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    issuer VARCHAR(255),
    issued_at DATE,
    credential_url VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS career_diary_entries (
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    user_id UUID NOT NULL,
    entry_date DATE NOT NULL,
    what_i_did TEXT NOT NULL,
    problems_solved TEXT,
    time_spent_minutes INTEGER,
    lessons_learned TEXT,
    status VARCHAR(50) NOT NULL DEFAULT 'RAW'
);

CREATE INDEX IF NOT EXISTS idx_diary_user_date ON career_diary_entries(user_id, entry_date);

CREATE TABLE IF NOT EXISTS diary_technologies (
    entry_id UUID NOT NULL REFERENCES career_diary_entries(id) ON DELETE CASCADE,
    technology VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS diary_people (
    entry_id UUID NOT NULL REFERENCES career_diary_entries(id) ON DELETE CASCADE,
    person VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS job_postings (
    id UUID PRIMARY KEY,
    created_at TIMESTAMPTZ NOT NULL,
    updated_at TIMESTAMPTZ NOT NULL,
    user_id UUID NOT NULL,
    title VARCHAR(500) NOT NULL,
    company VARCHAR(255) NOT NULL,
    source VARCHAR(50) NOT NULL,
    external_id VARCHAR(255),
    url VARCHAR(1000),
    description TEXT,
    seniority VARCHAR(50),
    work_model VARCHAR(50),
    location VARCHAR(255),
    language VARCHAR(50),
    compatibility_score NUMERIC(5,2),
    salary_min NUMERIC(12,2),
    salary_max NUMERIC(12,2),
    status VARCHAR(50) NOT NULL DEFAULT 'DISCOVERED',
    discovered_at TIMESTAMPTZ,
    applied_at TIMESTAMPTZ,
    UNIQUE(source, external_id)
);

CREATE TABLE IF NOT EXISTS job_technologies (
    job_id UUID NOT NULL REFERENCES job_postings(id) ON DELETE CASCADE,
    technology VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS job_benefits (
    job_id UUID NOT NULL REFERENCES job_postings(id) ON DELETE CASCADE,
    benefit VARCHAR(255) NOT NULL
);

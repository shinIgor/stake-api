use stake_project;
drop table if exists stake_project.chain_mt;
create table stake_project.chain_mt
(
    chain_mt_id  INT UNSIGNED NOT NULL,
    network_name VARCHAR(50)  NOT NULL,
    network_id   INT UNSIGNED NOT NULL,
    created_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

)
    ENGINE = INNODB
    CHARACTER SET utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '';
CREATE INDEX idx_chain_mt_updatedat_n1 ON stake_project.chain_mt (updated_at);
ALTER TABLE stake_project.chain_mt
    ADD PRIMARY KEY (chain_mt_id);
#=====================================================================================================
drop table if exists stake_project.contract_mt;
create table stake_project.contract_mt
(
    contract_mt_id       BIGINT UNSIGNED NOT NULL,
    chain_mt_id          INT UNSIGNED    NOT NULL,
    abi_mt_id            BIGINT UNSIGNED NOT NULL,
    contract_admin_mt_id INT UNSIGNED    NOT NULL,
    contract_name        VARCHAR(50)     NOT NULL,
    created_at           TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at           TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

)
    ENGINE = INNODB
    CHARACTER SET utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '';
ALTER TABLE stake_project.contract_mt
    ADD PRIMARY KEY (contract_mt_id);
#=====================================================================================================

drop table if exists stake_project.abi_mt;
create table stake_project.abi_mt
(
    abi_mt_id     BIGINT UNSIGNED NOT NULL,
    contract_name VARCHAR(50)     NOT NULL,
    abi           JSON            NOT NULL,
    created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

)
    ENGINE = INNODB
    CHARACTER SET utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '';
ALTER TABLE stake_project.abi_mt
    ADD PRIMARY KEY (abi_mt_id);
#=====================================================================================================
drop table if exists stake_project.contract_admin_mt;
create table stake_project.contract_admin_mt
(
    contract_admin_mt_id  INT UNSIGNED NOT NULL,
    public_address        VARCHAR(50)  NOT NULL,
    private_address_key   TEXT         NOT NULL,
    private_address_value TEXT         NOT NULL,
    created_at            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

)
    ENGINE = INNODB
    CHARACTER SET utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '';
CREATE INDEX idx_contract_admin_mt_publicaddress_n1 ON stake_project.contract_admin_mt (public_address);
ALTER TABLE stake_project.contract_admin_mt
    ADD PRIMARY KEY (contract_admin_mt_id);

#=====================================================================================================
drop table if exists stake_project.stake_pool_mt;
create table stake_project.stake_pool_mt
(
    stake_pool_mt_id     INT UNSIGNED    NOT NULL COMMENT '배포된 Staking Pool ID',
    contract_mt_id       BIGINT UNSIGNED NOT NULL COMMENT '배포된 Staking 컨트랙트 ID',
    staking_token_id     BIGINT UNSIGNED NOT NULL COMMENT '스테이킹 토큰 ERC20 컨트랙트 ID',
    contract_admin_mt_id INT UNSIGNED    NOT NULL COMMENT '컬트랙트 Owner Id',
    contract_address     VARCHAR(50)     NOT NULL COMMENT 'Stake 컨트랙트 주소',
    created_at           TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자',
    updated_at           TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 일자'

)
    ENGINE = INNODB
    CHARACTER SET utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '';

CREATE INDEX idx_stake_pool_mt_stakingtokenid ON stake_project.stake_pool_mt (staking_token_id);

ALTER TABLE stake_project.stake_pool_mt
    ADD PRIMARY KEY (stake_pool_mt_id);

#=====================================================================================================

drop table if exists stake_project.stake_reward_mt;
create table stake_project.stake_reward_mt
(
    stake_reward_mt_id   INT UNSIGNED    NOT NULL COMMENT '보상 Id',
    stake_pool_mt_id     INT UNSIGNED    NOT NULL COMMENT 'stake pool Id',
    reward_token_mt_id   BIGINT UNSIGNED NOT NULL COMMENT '보상 Token Contract ID',
    reward_amount        DECIMAL(65,18)  NOT NULL COMMENT '보상의 amount',
    reward_duration      BIGINT UNSIGNED NOT NULL COMMENT '보상의 기간',
    reward_per_token     BIGINT UNSIGNED NOT NULL COMMENT '보상의 초당 분배 갯수',
    reward_start_at      TIMESTAMP       NOT NULL COMMENT '보상의 시작 날짜',
    reward_period_finish TIMESTAMP       NOT NULL COMMENT '보상의 완료 날짜',
    created_at           TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 일자'
)
    ENGINE = INNODB
    CHARACTER SET utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '';
CREATE INDEX idx_stake_reward_mt_stakepoolmtid_n1 ON stake_project.stake_reward_mt (stake_pool_mt_id);
CREATE INDEX idx_stake_reward_mt_rewardtokenmtid_n1 ON stake_project.stake_reward_mt (reward_token_mt_id);

ALTER TABLE stake_project.stake_reward_mt
    ADD PRIMARY KEY (stake_reward_mt_id);
#=====================================================================================================
drop table if exists stake_project.stake_staker_ut;
create table stake_project.stake_staker_ut
(
    stake_staker_ut_id BIGINT UNSIGNED NOT NULL COMMENT 'Stake에 참여한 Staker 고유 ID',
    stake_pool_mt_id   INT UNSIGNED    NOT NULL COMMENT 'Stake Pool ID',
    wallet_address     VARCHAR(50)     NOT NULL COMMENT 'user wallet address',
    stake_amount       DECIMAL(65,18)  NOT NULL COMMENT 'user stake 금액',
    total_reward_paid  DECIMAL(65,18)  NOT NULL COMMENT '총 보상 수령 금액',
    stake_at           TIMESTAMP       NULL COMMENT 'Stake 시점',
    unstake_at         TIMESTAMP       NULL COMMENT 'unstake 시점',
    stake_flag         TINYINT         NOT NULL DEFAULT TRUE COMMENT '현재 Stake 상태 (0: stake 1: unStake)',
    created_at         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '데이터 생성 일자',
    updated_at         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '데이터 변경 일자'
)
    ENGINE = INNODB
    CHARACTER SET utf8mb4
    COLLATE = utf8mb4_general_ci COMMENT = '';


CREATE INDEX idx_stake_staker_ut_walletaddress_n1 ON stake_project.stake_staker_ut (wallet_address);

ALTER TABLE stake_project.stake_staker_ut
    ADD PRIMARY KEY (stake_staker_ut_id);

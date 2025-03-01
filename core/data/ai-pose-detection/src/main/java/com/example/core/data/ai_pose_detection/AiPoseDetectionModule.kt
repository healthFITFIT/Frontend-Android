package com.example.core.data.ai_pose_detection

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AiPoseDetectionModule {
    @Binds
    internal abstract fun bindAiPoseDetectionDataSource(
        aiPoseDetectionApi: AiPoseDetectionApi
    ): AiPoseDetectionDataSource
}